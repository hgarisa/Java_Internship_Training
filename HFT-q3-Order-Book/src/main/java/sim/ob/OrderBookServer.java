package sim.ob;

import quickfix.*;
import quickfix.field.*;
import quickfix.fix42.ExecutionReport;
import quickfix.fix42.NewOrderSingle;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A tiny single-symbol (AAPL) FIX 4.2 order book acceptor.
 * Supports:
 *   - NewOrderSingle (35=D) LIMIT
 *   - OrderCancelReplaceRequest (35=G) for price/qty changes
 *   - OrderCancelRequest (35=F)
 * Emits:
 *   - ExecutionReport (35=8) for NEW / PARTIAL_FILL / FILL / REPLACED / CANCELED
 *
 * Matching rule:
 *   - Price/time priority, partial fills allowed
 *   - Buys match when buyPx >= bestAskPx
 *   - Sells match when sellPx <= bestBidPx
 */
public class OrderBookServer implements Application {

    // ------- tiny in-memory order book (single symbol) -------
    static class ObOrder {
        final String clOrdId;
        final char side;         // Side.BUY (49) or Side.SELL (50)
        double price;
        double qty;
        double leaves;
        double cum;
        final long tsNanos;      // time priority

        ObOrder(String clOrdId, char side, double price, double qty, long tsNanos) {
            this.clOrdId = clOrdId;
            this.side = side;
            this.price = price;
            this.qty = qty;
            this.leaves = qty;
            this.cum = 0.0;
            this.tsNanos = tsNanos;
        }
    }

    // price/time: bids high->low, asks low->high
    private final PriorityBlockingQueue<ObOrder> bids =
            new PriorityBlockingQueue<>(64, Comparator
                    .<ObOrder>comparingDouble(o -> -o.price)  // higher first
                    .thenComparingLong(o -> o.tsNanos));

    private final PriorityBlockingQueue<ObOrder> asks =
            new PriorityBlockingQueue<>(64, Comparator
                    .comparingDouble((ObOrder o) -> o.price)   // lower first
                    .thenComparingLong(o -> o.tsNanos));

    // Fast lookup by ClOrdID so we can cancel/replace
    private final Map<String, ObOrder> byClOrdId = new ConcurrentHashMap<>();

    // Bookkeeping for ExecReports
    private final AtomicLong orderIdSeq = new AtomicLong(1);
    private final AtomicLong execIdSeq  = new AtomicLong(1);

    private volatile SessionID lastSession; // single client in this demo

    // ---- Application callbacks ----
    @Override public void onCreate(SessionID sessionID) {}
    @Override public void onLogon(SessionID sessionID) { System.out.println("EXOB logon: " + sessionID); lastSession = sessionID; }
    @Override public void onLogout(SessionID sessionID) { System.out.println("EXOB logout: " + sessionID); }
    @Override public void toAdmin(Message message, SessionID sessionID) {}
    @Override public void fromAdmin(Message message, SessionID sessionID) {}
    @Override public void toApp(Message message, SessionID sessionID) throws DoNotSend {}

    @Override
    public void fromApp(Message message, SessionID sessionID)
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {

        String msgType = message.getHeader().getString(MsgType.FIELD);

        switch (msgType) {
            case MsgType.ORDER_SINGLE -> onNewOrder((NewOrderSingle) message, sessionID);
            case MsgType.ORDER_CANCEL_REPLACE_REQUEST -> onReplace(message, sessionID);
            case MsgType.ORDER_CANCEL_REQUEST -> onCancel(message, sessionID);
            default -> System.out.println("EXOB got unsupported: " + msgType);
        }
    }

    // --------- Handlers ---------

    private void onNewOrder(NewOrderSingle nos, SessionID sid) throws FieldNotFound {
        // Read required FIX fields
        String clOrdId = nos.getString(ClOrdID.FIELD);
        char side      = nos.getChar(Side.FIELD);
        String symbol  = nos.isSetField(Symbol.FIELD) ? nos.getString(Symbol.FIELD) : "AAPL";
        double qty     = nos.getDouble(OrderQty.FIELD);
        char ordType   = nos.getChar(OrdType.FIELD);
        double px      = ordType == OrdType.LIMIT ? nos.getDouble(Price.FIELD) : Double.NaN;

        if (ordType != OrdType.LIMIT) {
            sendReject(sid, clOrdId, "Only LIMIT supported in this demo");
            return;
        }

        ObOrder o = new ObOrder(clOrdId, side, px, qty, System.nanoTime());
        byClOrdId.put(clOrdId, o);
        if (side == Side.BUY) bids.add(o); else asks.add(o);

        // Acknowledge NEW
        sendErNew(sid, clOrdId, o);

        // Try match after adding
        match(sid, symbol);
    }

    private void onReplace(Message msg, SessionID sid) throws FieldNotFound {
        String orig = msg.getString(OrigClOrdID.FIELD);
        String repl = msg.getString(ClOrdID.FIELD);

        ObOrder o = byClOrdId.remove(orig);
        if (o == null) { sendReject(sid, repl, "Unknown OrigClOrdID"); return; }

        // remove from queues
        removeFromBook(o);

        // apply new params (only price/qty for demo)
        if (msg.isSetField(Price.FIELD))    o.price  = msg.getDouble(Price.FIELD);
        if (msg.isSetField(OrderQty.FIELD)) {
            double newQty = msg.getDouble(OrderQty.FIELD);
            // leaves = newQty - cum (never negative)
            o.leaves = Math.max(0.0, newQty - o.cum);
            o.qty    = newQty;
        }
        // reinsert with fresh time priority
        o.leaves = Math.max(o.leaves, 0.0);
        ObOrder replaced = new ObOrder(repl, o.side, o.price, o.qty, System.nanoTime());
        replaced.cum = o.cum;
        replaced.leaves = o.leaves;
        byClOrdId.put(repl, replaced);
        if (replaced.side == Side.BUY) bids.add(replaced); else asks.add(replaced);

        sendErReplaced(sid, replaced);
        match(sid, "AAPL");
    }

    private void onCancel(Message msg, SessionID sid) throws FieldNotFound {
        String orig = msg.getString(OrigClOrdID.FIELD);
        String clId = msg.getString(ClOrdID.FIELD);

        ObOrder o = byClOrdId.remove(orig);
        if (o == null) { sendReject(sid, clId, "Unknown OrigClOrdID"); return; }
        removeFromBook(o);

        sendErCanceled(sid, clId, o);
    }

    private void removeFromBook(ObOrder o) {
        if (o.side == Side.BUY) bids.remove(o); else asks.remove(o);
    }

    // --------- Matching engine (very small) ---------
    private void match(SessionID sid, String symbol) {
        while (!bids.isEmpty() && !asks.isEmpty()) {
            ObOrder bid = bids.peek();
            ObOrder ask = asks.peek();
            if (bid.price < ask.price) break; // not marketable

            double tradeQty = Math.min(bid.leaves, ask.leaves);
            double tradePx  = ask.tsNanos <= bid.tsNanos ? ask.price : bid.price; // cross at maker price (very rough)

            // Update cum/leaves
            bid.leaves -= tradeQty; bid.cum += tradeQty;
            ask.leaves -= tradeQty; ask.cum += tradeQty;

            // Send fill ERs to the same client session (demo single participant)
            sendErFill(sid, bid, symbol, tradeQty, tradePx);
            sendErFill(sid, ask, symbol, tradeQty, tradePx);

            // Pop fully filled
            if (bid.leaves <= 0.0) { bids.poll(); byClOrdId.remove(bid.clOrdId); }
            if (ask.leaves <= 0.0) { asks.poll(); byClOrdId.remove(ask.clOrdId); }
        }
    }

    // --------- Execution Reports helpers ---------

    private void sendErNew(SessionID sid, String clOrdId, ObOrder o) {
        ExecutionReport er = baseEr(o, clOrdId);
        er.set(new ExecType(ExecType.NEW));
        er.set(new OrdStatus(OrdStatus.NEW));
        er.set(new LeavesQty(o.leaves));
        er.set(new CumQty(o.cum));
        er.set(new AvgPx(o.cum == 0 ? 0.0 : o.price));
        trySend(sid, er);
    }

    private void sendErReplaced(SessionID sid, ObOrder o) {
        ExecutionReport er = baseEr(o, o.clOrdId);
        er.set(new ExecType(ExecType.REPLACED));
        er.set(new OrdStatus(o.leaves == 0 ? OrdStatus.FILLED : (o.cum == 0 ? OrdStatus.NEW : OrdStatus.PARTIALLY_FILLED)));
        er.set(new LeavesQty(o.leaves));
        er.set(new CumQty(o.cum));
        er.set(new AvgPx(o.cum == 0 ? 0.0 : o.price));
        trySend(sid, er);
    }

    private void sendErCanceled(SessionID sid, String clId, ObOrder o) {
        ExecutionReport er = baseEr(o, clId);
        er.set(new ExecType(ExecType.CANCELED));
        er.set(new OrdStatus(OrdStatus.CANCELED));
        er.set(new LeavesQty(0));
        er.set(new CumQty(o.cum));
        er.set(new AvgPx(o.cum == 0 ? 0.0 : o.price));
        trySend(sid, er);
    }

    private void sendErFill(SessionID sid, ObOrder o, String symbol, double lastQty, double lastPx) {
        ExecutionReport er = baseEr(o, o.clOrdId);
        er.set(new ExecType(o.leaves == 0 ? ExecType.FILL : ExecType.PARTIAL_FILL));
        er.set(new OrdStatus(o.leaves == 0 ? OrdStatus.FILLED : OrdStatus.PARTIALLY_FILLED));
        er.set(new LastShares(lastQty));
        er.set(new LastPx(lastPx));
        er.set(new LeavesQty(o.leaves));
        er.set(new CumQty(o.cum));
        // avgPx: super-simplified here (not tracking weighted avg), fine for demo
        er.set(new AvgPx(lastPx));
        trySend(sid, er);
    }

    private ExecutionReport baseEr(ObOrder o, String clOrdId) {
        ExecutionReport er = new ExecutionReport(
                new OrderID(String.valueOf(orderIdSeq.getAndIncrement())),
                new ExecID(String.valueOf(execIdSeq.getAndIncrement())),
                new ExecTransType(ExecTransType.NEW),
                new ExecType(ExecType.NEW),
                new OrdStatus(OrdStatus.NEW),
                new Symbol("AAPL"),
                new Side(o.side),
                new LeavesQty(o.leaves),
                new CumQty(o.cum),
                new AvgPx(0));
        er.set(new ClOrdID(clOrdId));
        return er;
    }

    private void sendReject(SessionID sid, String clOrdId, String reason) {
        try {
            RejectLogon rej = new RejectLogon(reason);
            // For simplicity, we’ll just print; proper OrderCancelReject would be used for real flow.
            System.err.println("Reject: " + clOrdId + " - " + reason);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void trySend(SessionID sid, Message m) {
        try { Session.sendToTarget(m, sid); } catch (SessionNotFound e) { e.printStackTrace(); }
    }

    // ------------- main: boot acceptor -------------
    public static void main(String[] args) throws Exception {
        SessionSettings settings = new SessionSettings(
                OrderBookServer.class.getClassLoader().getResourceAsStream("ob-acceptor.cfg"));
        Application app = new OrderBookServer();
        MessageStoreFactory store = new FileStoreFactory(settings);
        LogFactory log = new FileLogFactory(settings);
        MessageFactory mf = new DefaultMessageFactory();
        Acceptor acceptor = new SocketAcceptor(app, store, settings, log, mf);
        acceptor.start();
        System.out.println("OrderBookServer up (port 9900). Waiting for orders…");
        Runtime.getRuntime().addShutdownHook(new Thread(acceptor::stop));
    }
}

