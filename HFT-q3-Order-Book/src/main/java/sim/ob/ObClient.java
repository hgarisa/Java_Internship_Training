package sim.ob;

import quickfix.*;
import quickfix.field.*;
import quickfix.fix42.ExecutionReport;
import quickfix.fix42.NewOrderSingle;
import quickfix.fix42.OrderCancelReplaceRequest;
import quickfix.fix42.OrderCancelRequest;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

/**
 * Sends:
 *   1) resting SELL 100 @ 100.20
 *   2) resting BUY  100 @  99.80
 *   3) aggressive BUY 150 @ 100.20  -> should PARTIAL FILL (100 filled, 50 leaves)
 *   4) REPLACE remaining BUY to 100.30 (price up)
 *   5) CANCEL any remaining
 *
 * Prints every ExecutionReport received.
 */
public class ObClient implements Application {
    private volatile SessionID sid;

    @Override public void onCreate(SessionID sessionID) {}
    @Override public void onLogon(SessionID sessionID) { System.out.println("TRDR logon: " + sessionID); sid = sessionID; }
    @Override public void onLogout(SessionID sessionID) { System.out.println("TRDR logout: " + sessionID); }
    @Override public void toAdmin(Message message, SessionID sessionID) {}
    @Override public void fromAdmin(Message message, SessionID sessionID) {}
    @Override public void toApp(Message message, SessionID sessionID) throws DoNotSend {}

    @Override
    public void fromApp(Message message, SessionID sessionID)
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        if (MsgType.EXECUTION_REPORT.equals(message.getHeader().getString(MsgType.FIELD))) {
            ExecutionReport er = (ExecutionReport) message;
            String cl = er.isSetField(ClOrdID.FIELD) ? er.getString(ClOrdID.FIELD) : "?";
            char ordStatus = er.getChar(OrdStatus.FIELD);
            double cum  = er.getDouble(CumQty.FIELD);
            double leaves = er.getDouble(LeavesQty.FIELD);
            double lastQty = er.isSetField(LastShares.FIELD) ? er.getDouble(LastShares.FIELD) : 0.0;
            double lastPx  = er.isSetField(LastPx.FIELD) ? er.getDouble(LastPx.FIELD) : 0.0;
            System.out.printf("ER  clOrdId=%s  status=%c  last=%s@%s  cum=%s  leaves=%s%n",
                    cl, ordStatus, fmt(lastQty), fmt(lastPx), fmt(cum), fmt(leaves));
        }
    }

    private static String fmt(double d) { return String.format(java.util.Locale.US, "%.4f", d); }

    // ---- helper builders ----
    private static NewOrderSingle nos(String clId, char side, double qty, double px) {
        NewOrderSingle m = new NewOrderSingle(new ClOrdID(clId),
                new HandlInst(HandlInst.AUTOMATED_EXECUTION_ORDER_PRIVATE_NO_BROKER_INTERVENTION),
                new Symbol("AAPL"),
                new Side(side),
                new TransactTime(),
                new OrdType(OrdType.LIMIT));
        m.set(new OrderQty(qty));
        m.set(new Price(px));
        m.set(new TimeInForce(TimeInForce.DAY));
        return m;
    }

    private static OrderCancelReplaceRequest repl(String orig, String replId, Double newQty, Double newPx, char side) {
        OrderCancelReplaceRequest m = new OrderCancelReplaceRequest(
                new OrigClOrdID(orig), new ClOrdID(replId), new HandlInst(HandlInst.AUTOMATED_EXECUTION_ORDER_PRIVATE_NO_BROKER_INTERVENTION),
                new Symbol("AAPL"), new Side(side), new TransactTime(),
                new OrdType(OrdType.LIMIT));
        if (newQty != null) m.set(new OrderQty(newQty));
        if (newPx  != null) m.set(new Price(newPx));
        return m;
    }

    private static OrderCancelRequest cancel(String orig, String cancelId, char side) {
        return new OrderCancelRequest(new OrigClOrdID(orig), new ClOrdID(cancelId),
                new Symbol("AAPL"), new Side(side), new TransactTime());
    }

    // ------------- main: boot initiator and run the scenario -------------
    public static void main(String[] args) throws Exception {
        SessionSettings settings = new SessionSettings(
                ObClient.class.getClassLoader().getResourceAsStream("ob-initiator.cfg"));
        Application app = new ObClient();
        MessageStoreFactory store = new FileStoreFactory(settings);
        LogFactory log = new FileLogFactory(settings);
        MessageFactory mf = new DefaultMessageFactory();
        Initiator initiator = new SocketInitiator(app, store, settings, log, mf);
        initiator.start();

        // Wait until session is logged on
        SessionID s;
        while ((s = ((ObClient) app).sid) == null || !Session.lookupSession(s).isLoggedOn()) {
            Thread.sleep(100);
        }

        // 1) Resting SELL 100 @ 100.20
        String s1 = "S-" + UUID.randomUUID();
        Session.sendToTarget(nos(s1, Side.SELL, 100, 100.20), s);

        // 2) Resting BUY 100 @ 99.80
        String b1 = "B-" + UUID.randomUUID();
        Session.sendToTarget(nos(b1, Side.BUY, 100, 99.80), s);

        Thread.sleep(200); // let NEW ERs print

        // 3) Aggressive BUY 150 @ 100.20  (should match 100 against resting SELL; 50 leaves)
        String b2 = "B2-" + UUID.randomUUID();
        Session.sendToTarget(nos(b2, Side.BUY, 150, 100.20), s);

        Thread.sleep(500);

        // 4) REPLACE remaining of B2 to higher price 100.30 (should try to match any opposite liquidity)
        String b2r = "B2R-" + UUID.randomUUID();
        Session.sendToTarget(repl(b2, b2r, null, 100.30, Side.BUY), s);

        Thread.sleep(500);

        // 5) CANCEL whatever is left on B2R
        String b2c = "B2C-" + UUID.randomUUID();
        Session.sendToTarget(cancel(b2r, b2c, Side.BUY), s);

        Thread.sleep(500);
        System.out.println("Scenario complete. Logging out…");
        initiator.stop();
    }
}
