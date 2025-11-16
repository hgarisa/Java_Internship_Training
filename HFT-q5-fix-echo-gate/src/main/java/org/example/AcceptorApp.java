package org.example;

import quickfix.*;
import quickfix.field.*;
import quickfix.fix44.NewOrderSingle;
import quickfix.fix44.ExecutionReport;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class AcceptorApp extends MessageCracker implements Application {
    private final AtomicLong orderIdSeq = new AtomicLong(1);
    private final AtomicLong execIdSeq  = new AtomicLong(1);

    @Override public void onCreate(SessionID sessionID) {}
    @Override public void onLogon(SessionID sessionID)  { System.out.println("ACCEPTOR logon: " + sessionID); }
    @Override public void onLogout(SessionID sessionID) { System.out.println("ACCEPTOR logout: " + sessionID); }

    @Override public void toAdmin(Message message, SessionID sessionID) {}
    @Override public void fromAdmin(Message message, SessionID sessionID)
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {}

    @Override public void toApp(Message message, SessionID sessionID) throws DoNotSend {}

    @Override public void fromApp(Message message, SessionID sessionID)
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        crack(message, sessionID); // routes to the matching onMessage(...)
    }

    // @Handler (optional but nice if you're on a recent QFJ); reflection will still find this by signature.
    public void onMessage(NewOrderSingle nos, SessionID sessionID) throws FieldNotFound {
        String clOrdId = nos.getClOrdID().getValue();
        char   side    = nos.getSide().getValue();
        double px      = nos.isSetPrice() ? nos.getPrice().getValue() : 0.0;
        double qty     = nos.getOrderQty().getValue();

        OrderID orderID = new OrderID("O" + orderIdSeq.getAndIncrement());
        ExecID  execID  = new ExecID("E" + execIdSeq.getAndIncrement());

        // FIX 4.4 constructor (this is why we must import quickfix.fix44.ExecutionReport)
        ExecutionReport er = new ExecutionReport(
                orderID,
                execID,
                new ExecType(ExecType.NEW),
                new OrdStatus(OrdStatus.NEW),
                new Side(side),
                new LeavesQty(qty),
                new CumQty(0),
                new AvgPx(px)
        );

        // echo back the client’s ClOrdID so they can correlate
        er.set(new ClOrdID(clOrdId));
        // timestamp of this event
        er.set(new TransactTime());

        // pass through fields from the original order if present
        if (nos.isSetSymbol()) er.set(nos.getSymbol());
        if (nos.isSetPrice())  er.set(nos.getPrice());

        // no fill happened (it’s just an ack/new)
        er.set(new LastPx(0));
        er.set(new LastQty(0));

        try {
            Session.sendToTarget(er, sessionID);
        } catch (SessionNotFound e) {
            e.printStackTrace();
        }
    }
}
