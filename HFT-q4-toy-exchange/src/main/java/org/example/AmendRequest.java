package org.example;

public class AmendRequest
{
    private final long tsNanos;
    private final long orderId;
    private final Long newQty;    // null = no change
    private final Long newPrice;  // null = no change


    public AmendRequest(long tsNanos, long orderId, Long newQty, Long newPrice) {
        this.tsNanos = tsNanos;
        this.orderId = orderId;
        this.newQty = newQty;
        this.newPrice = newPrice;
    }

    public long tsNanos()  { return tsNanos; }
    public long orderId()  { return orderId; }
    public Long newQty()   { return newQty; }
    public Long newPrice() { return newPrice; }

    // Why is it big letter Long for "newQty and newPrice" and small letter long for "tsNanos and orderId" ?

    // newQty and newPrice uses Long (big L) so that they can be null. In an amend message, null means “don’t change this field.”
    // Primitives (long) can’t be null , so that is why use the wrapper type to encode “no change”.
    // tsNanos / orderId use long (small l) because they’re always required.
    // Long = object wrapper for long. Can be null, involves boxing/unboxing.
    // long = primitive. Cannot be null, zero default if uninitialized in fields, no boxing.




}
