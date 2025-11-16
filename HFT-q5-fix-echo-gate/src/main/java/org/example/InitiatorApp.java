package org.example;

import quickfix.*;
import quickfix.field.ClOrdID;
import quickfix.field.OrderQty;
import quickfix.field.Side;
import quickfix.field.TransactTime;
import quickfix.field.OrdType;        // <-- add
import quickfix.field.Price;          // <-- add
import quickfix.field.Symbol;         // <-- add
import quickfix.field.TimeInForce;    // <-- optional but useful
import quickfix.fix44.ExecutionReport; // <-- use FIX.4.4
import quickfix.fix44.NewOrderSingle;  // <-- use FIX.4.4

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class InitiatorApp extends MessageCracker implements Application {
    private volatile SessionID sessionID;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final Map<String, Long> sendTimes = new ConcurrentHashMap<>();
    private final Random r = new Random();
    private final Path csv = Paths.get("target", "latencies.csv");
    private BufferedWriter writer;

    @Override public void onCreate(SessionID sessionID) {}
    @Override public void onLogon(SessionID sessionID) { this.sessionID = sessionID; System.out.println("INITIATOR logon: " + sessionID); startSender(); }
    @Override public void onLogout(SessionID sessionID) { System.out.println("INITIATOR logout: " + sessionID); stopSender(); }
    @Override public void toAdmin(Message message, SessionID sessionID) {}
    @Override public void fromAdmin(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {}
    @Override public void toApp(Message message, SessionID sessionID) throws DoNotSend {}
    @Override public void fromApp(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType { crack(message, sessionID); }

    private void startSender() {
        try {
            Files.createDirectories(csv.getParent());
            boolean newFile = Files.notExists(csv);
            writer = Files.newBufferedWriter(csv, CREATE, APPEND);
            if (newFile) writer.write("clOrdId,sendNs,recvNs,rttNs\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scheduler.scheduleAtFixedRate(this::sendNOS, 0, 250, TimeUnit.MILLISECONDS);
    }

    private void stopSender() {
        scheduler.shutdownNow();
        try { if (writer != null) writer.flush(); } catch (IOException ignored) {}
    }

    private void sendNOS() {
        Session session = Session.lookupSession(sessionID);
        if (session == null || !session.isLoggedOn()) return;

        String clOrdId = "C" + System.nanoTime();
        char side = r.nextBoolean() ? Side.BUY : Side.SELL;
        double px  = 100 + r.nextInt(5); // 100..104
        double qty = 1   + r.nextInt(5); // 1..5

        // FIX.4.4 constructor: (ClOrdID, Side, TransactTime, OrdType)
        NewOrderSingle nos = new NewOrderSingle(
                new ClOrdID(clOrdId),
                new Side(side),
                new TransactTime(),
                new OrdType(OrdType.LIMIT)
        );
        nos.set(new Symbol("ECHO"));
        nos.set(new OrderQty(qty));
        nos.set(new Price(px));
        nos.set(new TimeInForce(TimeInForce.DAY)); // optional but typical

        long now = System.nanoTime();
        sendTimes.put(clOrdId, now);
        try {
            Session.sendToTarget(nos, sessionID);
        } catch (SessionNotFound e) {
            e.printStackTrace();
        }
    }

    @Handler
    public void onMessage(ExecutionReport er, SessionID sid) throws FieldNotFound {
        String clOrdId = er.getClOrdID().getValue();
        System.out.println("Got ER for " + er.getClOrdID().getValue());
        Long sendNs = sendTimes.remove(clOrdId);
        long recvNs = System.nanoTime();
        long rtt = (sendNs != null) ? (recvNs - sendNs) : -1L;
        try {
            if (writer != null) writer.write(clOrdId + "," + (sendNs == null ? -1L : sendNs) + "," + recvNs + "," + rtt + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
