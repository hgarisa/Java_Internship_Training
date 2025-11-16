package org.example;
/*

The Order class is a tiny data container that represents a trade order in a market system
-i.e., it is an instruction to BUY or SELL a certain quantity at a given price,
 with a unique id and a timestamp.

 Why is the class Order marked final?

final on a class means no one can extend (subclass) it.

Why do that?

Avoid surprises: You don’t want someone to change how an Order behaves by subclassing it.
Keep invariants safe: Whatever rules you intend for Order stay intact.
API stability: Code that relies on Order won’t get odd behavior from custom subclasses.

Why are all the fields long data type?

All the numbers are of long data type because ,

id (long): Order IDs can get very large across a busy system; long gives a big safe range.
price (long): In trading we avoid double because it can introduce rounding errors.
We store price as integer ticks (e.g., cents) using a long.
qty (long): Quantities can also be large; long avoids overflow that int might hit.
tsNanos (long): Timestamps in nanoseconds need 64 bits (e.g., epoch-nanos).
Int can’t hold that range.

Why not use double or BigDecimal?

double: Fast but can be inexact (floating-point rounding issues) → bad for money.
BigDecimal : Exact but slower and allocates objects → not ideal for low-latency systems.
long + a scaling rule (like “price is in cents”): Exact and fast.

Why are all the fields in the Order class final except qty ?

An Order is a live instruction that can be partially filled.

id, side, price, tsNanos are facts fixed at creation → marked final so they never change.
qty is the remaining amount. It must decrease as fills happen (partial fills),
 so it’s the only mutable field.
 The order is a ticket on the wall; only the “how much left” number gets updated.

*/


enum Side{BUY , SELL}
public final class Order
{
   final long id;
  final  Side side;
   long price;

    long qty;

   final long tsNanos;

    Order(long id, Side side, long price, long qty, long tsNanos)
    {

        this.id = id;
        this.side = side;
        this.price = price;
        this.qty = qty;
        this.tsNanos = tsNanos;
    }

    public String toString()
    {
        return  "Order : " + id + " , " + side + ", pe =  " + price + " , qty : " + qty ;
    }


}
