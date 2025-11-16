package org.example;

public class CancelEvent
{
    private final long tsNanos;
    private final long orderId;

    private final boolean ok;      // true if an order was removed
    private final String reason;   // e.g., "not_found" when nothing to cancel


    public CancelEvent(long tsNanos, long orderId, boolean ok, String reason) {
        this.tsNanos = tsNanos;
        this.orderId = orderId;
        this.ok = ok;
        this.reason = reason;
    }

    public long tsNanos() { return tsNanos; }
    public long orderId() { return orderId; }
    public boolean ok()   { return ok; }
    public String reason(){ return reason; }

    @Override public String toString() {
        return " CancelEvent {ts=" + tsNanos + ", id=" + orderId +
                ", ok= " + ok + ok +  " reason = " + reason + "} ";
    }



}
