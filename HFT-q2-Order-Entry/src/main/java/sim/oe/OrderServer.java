package sim.oe;

import quickfix.*;
import quickfix.field.*;
import quickfix.fix42.ExecutionReport;
import quickfix.fix42.NewOrderSingle;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.TimeUnit;

public class OrderServer implements Application {

    // Minimal "matching engine": just store orders and send NEW then FILLED after a short delay
    private final ScheduledExecutorService timers = Executors.newSingleThreadScheduledExecutor();
    private final Map<String, Double> lastPx = new ConcurrentHashMap<>();

    @Override public void onCreate(SessionID sessionID) {}
    @Override public void onLogon(SessionID sessionID) { System.out.println("EXCH logon: " + sessionID); }
    @Override public void onLogout(SessionID sessionID) { System.out.println("EXCH logout: " + sessionID); }
    @Override public void toAdmin(Message message, SessionID sessionID) {}
    @Override public void fromAdmin(Message message, SessionID sessionID) {}
    @Override public void toApp(Message message, SessionID sessionID) {}

    @Override
    public void fromApp(Message message, SessionID sessionID)
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {

        final String msgType = message.getHeader().getString(MsgType.FIELD);

        if (MsgType.ORDER_SINGLE.equals(msgType)) {
            NewOrderSingle nos = (NewOrderSingle) message;

            String clOrdId = nos.getString(ClOrdID.FIELD);
            char side = nos.getChar(Side.FIELD);
            String symbol = nos.getString(Symbol.FIELD);
            double qty = nos.getDouble(OrderQty.FIELD);
            double px  = nos.getDouble(Price.FIELD);

            // Store a last price per symbol (just for pretty fills)
            lastPx.put(symbol, px);

            // 1) Acknowledge NEW
            ExecutionReport ack = buildExecReport(
                    "NEW-" + clOrdId,            // ExecID
                    clOrdId,                     // OrderID (use clOrdId for simplicity)
                    symbol, side, qty, 0, 0, px, OrdStatus.NEW);
            send(ack, sessionID);

            // 2) Simulate full fill after 300ms (you could randomize for partial fills)
            timers.schedule(() -> {
                double last = lastPx.getOrDefault(symbol, px);
                ExecutionReport fill = buildExecReport(
                        "FILL-" + clOrdId, clOrdId, symbol, side,
                        qty, /*cum*/qty, /*leaves*/0, last, OrdStatus.FILLED);
                send(fill, sessionID);
            }, 300, TimeUnit.MILLISECONDS);
        }
    }

    private static void send(Message m, SessionID sid) {
        try { Session.sendToTarget(m, sid); }
        catch (SessionNotFound e) { e.printStackTrace(); }
    }

    private static ExecutionReport buildExecReport(
            String execId, String orderId, String symbol, char side,
            double orderQty, double cumQty, double leavesQty, double price, char ordStatus) {

        ExecutionReport er = new ExecutionReport(
                new OrderID(orderId),
                new ExecID(execId),
                new ExecTransType(ExecTransType.NEW),
                new ExecType(ordStatus == OrdStatus.FILLED ? ExecType.FILL : ExecType.NEW),
                new OrdStatus(ordStatus),
                new Symbol(symbol),
                new Side(side),
                new LeavesQty(leavesQty),
                new CumQty(cumQty),
                new AvgPx(price)
        );
        er.set(new OrderQty(orderQty));
        er.set(new TransactTime());
        return er;
    }

    public static void main(String[] args) throws Exception {
        SessionSettings settings = new SessionSettings(
                OrderServer.class.getClassLoader().getResourceAsStream("oe-acceptor.cfg"));
        Application app = new OrderServer();
        MessageStoreFactory store = new FileStoreFactory(settings);
        LogFactory log = new FileLogFactory(settings);
        MessageFactory mf = new DefaultMessageFactory();
        Acceptor acceptor = new SocketAcceptor(app, store, settings, log, mf);
        acceptor.start();
        System.out.println("OrderServer up @ " + Instant.now() + " (port 9899).");
        System.out.println("Waiting for NewOrderSingle (35=D). Ctrl+C to exit.");
        Runtime.getRuntime().addShutdownHook(new Thread(acceptor::stop));
    }
}

