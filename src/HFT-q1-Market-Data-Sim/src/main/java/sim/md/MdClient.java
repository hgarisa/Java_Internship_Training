package sim.md;

import quickfix.*;
import quickfix.Message;
import quickfix.MessageFactory;
import quickfix.field.*;
import quickfix.fix42.*;
import java.time.Instant;

public class MdClient implements Application {

    private SessionID sessionID;

    @Override public void onCreate(SessionID id) { }
    @Override public void onLogon(SessionID id) { System.out.println("CLIENT logon: " + id); sessionID = id; }
    @Override public void onLogout(SessionID id) { System.out.println("CLIENT logout: " + id); }
    @Override public void toAdmin(Message msg, SessionID id) {}
    @Override public void fromAdmin(Message msg, SessionID id) {}
    @Override public void toApp(Message msg, SessionID id) throws DoNotSend {}
    @Override public void fromApp(Message msg, SessionID id) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        System.out.println("CLIENT got app msg: " + msg);
    }

    public static void main(String[] args) throws Exception {
        SessionSettings settings = new SessionSettings(
                MdClient.class.getClassLoader().getResourceAsStream("md-initiator.cfg"));
        Application app = new MdClient();
        MessageStoreFactory store = new FileStoreFactory(settings);
        LogFactory log = new FileLogFactory(settings);
        MessageFactory mf = new DefaultMessageFactory();
        Initiator initiator = new SocketInitiator(app, store, settings, log, mf);

        initiator.start();
        System.out.println("MdClient up @ " + Instant.now());

        // Wait for logon
        Thread.sleep(1500);

        // Build a simple MarketDataRequest for AAPL
        MarketDataRequest req = new MarketDataRequest(
                new MDReqID("md-1"),
                new SubscriptionRequestType(SubscriptionRequestType.SNAPSHOT_UPDATES),
                new MarketDepth(1));

        MarketDataRequest.NoMDEntryTypes t1 = new MarketDataRequest.NoMDEntryTypes();
        t1.set(new MDEntryType(MDEntryType.BID));
        req.addGroup(t1);
        MarketDataRequest.NoMDEntryTypes t2 = new MarketDataRequest.NoMDEntryTypes();
        t2.set(new MDEntryType(MDEntryType.OFFER));
        req.addGroup(t2);

        MarketDataRequest.NoRelatedSym sym = new MarketDataRequest.NoRelatedSym();
        sym.set(new Symbol("AAPL"));
        req.addGroup(sym);

        // Send to server
        for (SessionID sid : initiator.getSessions()) {
            if (Session.doesSessionExist(sid) && Session.lookupSession(sid).isLoggedOn()) {
                Session.sendToTarget(req, sid);
            }
        }

        // Keep client alive to receive updates
        Thread.sleep(10_000);
        initiator.stop();
    }
}


