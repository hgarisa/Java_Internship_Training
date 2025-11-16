package org.example;

public class AmendEvent
{
    private final long tsNanos;
    private final long orderId;
    private final boolean ok;
    private final String reason; // null if ok

    public AmendEvent(long tsNanos, long orderId, boolean ok, String reason) {
        this.tsNanos = tsNanos;
        this.orderId = orderId;
        this.ok = ok;
        this.reason = reason;
    }

    public long tsNanos() { return tsNanos; }
    public long orderId() { return orderId; }
    public boolean ok()   { return ok; }
    public String reason(){ return reason; }



}
