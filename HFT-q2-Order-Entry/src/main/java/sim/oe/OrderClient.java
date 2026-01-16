package sim.oe;

import quickfix.*;
import quickfix.field.*;
import quickfix.fix42.ExecutionReport;
import quickfix.fix42.NewOrderSingle;

import java.time.Instant;
import java.util.UUID;

public class OrderClient implements Application {

    private Initiator initiator;

    @Override public void onCreate(SessionID sessionID) {}
    @Override public void toAdmin(Message message, SessionID sessionID) {}
    @Override public void fromAdmin(Message message, SessionID sessionID) {}
    @Override public void toApp(Message message, SessionID sessionID) {}
    @Override public void onLogout(SessionID sessionID) { System.out.println("CLIENT logout: " + sessionID); }

    @Override
    public void onLogon(SessionID sessionID) {
        System.out.println("CLIENT logon: " + sessionID);
        try {
            // Build a simple LIMIT buy
            String clOrdId = UUID.randomUUID().toString();
            String symbol = "AAPL";
            double qty = 100;
            double px  = 100.05;

            NewOrderSingle nos = new NewOrderSingle(
                    new ClOrdID(clOrdId),
                    new HandlInst(HandlInst.AUTOMATED_EXECUTION_ORDER_PRIVATE_NO_BROKER_INTERVENTION),
                    new Symbol(symbol),
                    new Side(Side.BUY),
                    new TransactTime(),
                    new OrdType(OrdType.LIMIT)
            );
            nos.set(new OrderQty(qty));
            nos.set(new Price(px));
            // market depth/other optionals could go here

            System.out.println("CLIENT sending NOS " + clOrdId + " " + symbol + " " + qty + "@" + px);
            Session.sendToTarget(nos, sessionID);

        } catch (SessionNotFound e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fromApp(Message message, SessionID sessionID)
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        String type = message.getHeader().getString(MsgType.FIELD);
        if (MsgType.EXECUTION_REPORT.equals(type)) {
            ExecutionReport er = (ExecutionReport) message;
            String ordId = er.getString(OrderID.FIELD);
            char status = er.getChar(OrdStatus.FIELD);
            double cum = er.getDouble(CumQty.FIELD);
            double leaves = er.getDouble(LeavesQty.FIELD);
            double avgPx = er.getDouble(AvgPx.FIELD);
            System.out.printf("CLIENT got ER: orderId=%s status=%s cum=%.0f leaves=%.0f avgPx=%.5f%n",
                    ordId, status, cum, leaves, avgPx);
        }
    }

    public static void main(String[] args) throws Exception {
        SessionSettings settings = new SessionSettings(
                OrderClient.class.getClassLoader().getResourceAsStream("oe-initiator.cfg"));
        Application app = new OrderClient();
        MessageStoreFactory store = new FileStoreFactory(settings);
        LogFactory log = new FileLogFactory(settings);
        MessageFactory mf = new DefaultMessageFactory();
        Initiator initiator = new SocketInitiator(app, store, settings, log, mf);
        ((OrderClient) app).initiator = initiator;

        initiator.start();
        System.out.println("OrderClient up @ " + Instant.now());

        // Keep alive a bit to receive fills; then stop cleanly
        Thread.sleep(5_000);
        initiator.stop();
    }
}
