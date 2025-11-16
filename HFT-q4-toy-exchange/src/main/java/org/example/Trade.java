package org.example;
/*

What is the difference between the Trade class and the Order class ?

Why do we have both of them separately ?

Why cant we just have only one of them ?


Order = an intent (“I want to buy/sell X at price P, up to quantity Q”).
Trade = an outcome (“A buy and a sell actually matched for q at price p at time t”).

Here’s why we keep them separate:

1. Different lifecycles : Order lives in the book, can sit there, be partially filled, amended, or canceled.
 Its remaining qty changes. Trade is the  final fact. Once a match happens, we record it and it never changes .

 2. One-to-many relationship : A single Order can generate many Trades.
 Each Trade links exactly one buyer and one seller (often by their order IDs).

For example : Lets do a step by step scenario :

(1) . One buy arrives and waits : Buy order: id=101, price=105, qty=100
Meaning: “I’ll buy up to 100 units, but I won’t pay more than 105.”
No sells yet ⇒ nothing happens. The order sits in the book, waiting for the seller.

(2) . A small sell appears and crosses : Sell order: id=201, price=105, qty=30
Meaning: “I’ll sell up to 30 units, but not for less than 105.”
Now we check: is buy price (105) ≥ sell price (105)? Yes → they match.

We create a Trade record for the matched qty = min(100, 30) = 30 at price 105.
We subtract from each order’s remaining quantity:
Buyer had 100 → now 70 left.
Seller had 30 → now 0 left (sold out) → seller order leaves the book.

(3).Later, another sell arrives

Sell order: id=202, price=105, qty=50

Check crossing again: buyer’s price 105 ≥ seller’s 105 → match.
Create another Trade for 50 units at Price 105.

Update remaining:

Buyer 70 → 20 units left.

Seller (2) 50 →  has 0 units left.

Now the original buy order still exists with 20 remaining (it can match again later).

The Trades we created (30 and 50) are permanent history entries—they never change.

Why “Order changes, Trade doesn’t”

The Order is like an open tab: its remaining qty goes down as you get served.

Each Trade is a receipt for what actually got exchanged at a moment in time. Receipts don’t change.

Why are all the fields in the Trade class final ?

A Trade is a historical record of something that already happened.
Once a match occurs, the trade’s buyId, sellId, price, qty, tsNanos should never change.
Therefore all fields are final.

A trade is a printed receipt—no edits afterward.


*/

public final class Trade
{

    final long buyId;
    final long sellId;
    final long price;
    final long qty;
    final long tsNanos;


    Trade(long buyId, long sellId, long price, long qty, long tsNanos) {
        this.buyId = buyId; this.sellId = sellId; this.price = price; this.qty = qty; this.tsNanos = tsNanos;
    }


    @Override public String toString() {
        return " Trade : " + " buy= : " + buyId + ", sell= " + sellId + ", px = " + price + " , qty = " + qty + "}";
    }
}
