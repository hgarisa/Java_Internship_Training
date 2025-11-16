package org.example;

// CancelRequest: sent from client -> exchange (asks the engine to cancel an order)
public class CancelRequest
{
    private final long tsNanos;
    private final long orderId;

    public CancelRequest(long tsNanos, long orderId) {
        this.tsNanos = tsNanos;
        this.orderId = orderId;
    }

    public long tsNanos() { return tsNanos; }
    public long orderId() { return orderId; }


    public String toString() {
        return " CancelRequest {ts= " + tsNanos + ", id=" + orderId + "}";
    }



}
