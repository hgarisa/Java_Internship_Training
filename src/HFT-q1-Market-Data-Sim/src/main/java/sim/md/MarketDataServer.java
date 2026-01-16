package sim.md;

import quickfix.*;
import quickfix.Message;
import quickfix.MessageFactory;
import quickfix.field.*;
import quickfix.fix42.*;

import java.time.Instant;
import java.util.concurrent.*;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;


public class MarketDataServer implements Application {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private volatile boolean streaming = false;
    private final ConcurrentMap<String, Double> lastPx = new ConcurrentHashMap<>();

    @Override public void onCreate(SessionID sessionID) {}
    @Override public void onLogon(SessionID sessionID) { System.out.println("MD-SRV logon: " + sessionID); }
    @Override public void onLogout(SessionID sessionID) { System.out.println("MD-SRV logout: " + sessionID); streaming = false; }
    @Override public void toAdmin(Message message, SessionID sessionID) {}
    @Override public void fromAdmin(Message message, SessionID sessionID) {}
    @Override public void toApp(Message message, SessionID sessionID) throws DoNotSend {}

    // Receive MarketDataRequest (MsgType=V) and start streaming
    @Override
    public void fromApp(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        if (MsgType.MARKET_DATA_REQUEST.equals(message.getHeader().getString(MsgType.FIELD))) {
            MarketDataRequest req = (MarketDataRequest) message;
            String symbol = "AAPL"; // default fallback
            if (req.isSetNoRelatedSym() && req.getNoRelatedSym().getValue() > 0) {
                MarketDataRequest.NoRelatedSym g = new MarketDataRequest.NoRelatedSym();
                req.getGroup(1, g);                     // read the first RelatedSym group
                symbol = g.getString(Symbol.FIELD);     // extract 55=Symbol
            }
            System.out.println("MD request for " + symbol);

            System.out.println("MD request for " + symbol);
            lastPx.putIfAbsent(symbol, 100.00);
            startStreaming(sessionID, symbol);
        }
    }

    // Every 500ms publish a snapshot (W) and sometimes an incremental (X)
    private void startStreaming(SessionID sessionID, String symbol) {
        if (streaming) return;
        streaming = true;

        scheduler.scheduleAtFixedRate(() -> {
            try {
                lastPx.compute(symbol, (s, p) -> (p == null ? 100.0 : p) + (Math.random() - 0.5) * 0.10);
                double px = lastPx.get(symbol);

                MarketDataSnapshotFullRefresh snap = new MarketDataSnapshotFullRefresh();
                snap.set(new Symbol(symbol));

                var bid = new MarketDataSnapshotFullRefresh.NoMDEntries();
                bid.set(new MDEntryType(MDEntryType.BID));
                bid.set(new MDEntryPx(px - 0.01));
                bid.set(new MDEntrySize(100));
                snap.addGroup(bid);

                var ask = new MarketDataSnapshotFullRefresh.NoMDEntries();
                ask.set(new MDEntryType(MDEntryType.OFFER));
                ask.set(new MDEntryPx(px + 0.01));
                ask.set(new MDEntrySize(100));
                snap.addGroup(ask);

                Session.sendToTarget(snap, sessionID);

                if (Math.random() < 0.25) {
                    MarketDataIncrementalRefresh inc = new MarketDataIncrementalRefresh();
                    var ent = new MarketDataIncrementalRefresh.NoMDEntries();
                    ent.set(new MDUpdateAction(MDUpdateAction.CHANGE));
                    ent.set(new MDEntryType(MDEntryType.TRADE));
                    ent.set(new Symbol(symbol));
                    ent.set(new MDEntryPx(px));
                    ent.set(new MDEntrySize(10));
                    inc.addGroup(ent);
                    Session.sendToTarget(inc, sessionID);
                }
            } catch (Exception e) { e.printStackTrace(); }
        }, 0, 500, TimeUnit.MILLISECONDS);
    }

    // Boot the acceptor (server)
    public static void main(String[] args) throws Exception {
        SessionSettings settings = new SessionSettings(
                MarketDataServer.class.getClassLoader().getResourceAsStream("md-acceptor.cfg"));
        Application app = new MarketDataServer();
        MessageStoreFactory store = new FileStoreFactory(settings);
        LogFactory log = new FileLogFactory(settings);
        MessageFactory mf = new DefaultMessageFactory();
        Acceptor acceptor = new SocketAcceptor(app, store, settings, log, mf);
        acceptor.start();
        System.out.println("MarketDataServer up @ " + Instant.now() + " (port 9898).");
        System.out.println("Will stream after a MarketDataRequest (V). Ctrl+C to exit.");
        Runtime.getRuntime().addShutdownHook(new Thread(acceptor::stop));
    }
}


