package org.example;

// 2) ORDER BOOK (price-time priority)
// We'll use two PriorityQueues:
// - bids: highest price first, then earlier ts first
// - asks: lowest price first, then earlier ts first

import java.util.ArrayList;
import java.util.List;
import  java.util.concurrent.PriorityBlockingQueue;

/*

Why do  we use PriorityBlockingQueue in the Toy Exchange ?

We keep two priority queues:

bids : we want the highest price first → we use a comparator that sorts descending by price,
then by earlier timestamp (price-time priority).
asks: we want the lowest price first → comparator ascending by price, then earlier timestamp.

What does the OrderBook constructor do ?
It builds two priority queues. One for buy orders (bids) and
one for sale orders (asks)—and tells each queue how to rank orders.

PriorityBlockingQueue<>(1024, …) : 1024 is  just an initial capacity of the queue
(it can grow large than that number; it’s not a hard limit)
The second argument is a Comparator (a tiny function that says “which order comes first?”).

For the comparator for bids (buys) , what happens if we say  o1.price first then o2.price ,
 like this ((o2.price, o1.price) . Will it still be the same higher price first ?
 Answer is  No—if you flip it, you change the sort order.

Why Long.compare(o2.price, o1.price) for bids?
PriorityBlockingQueue puts the “smallest” element (by comparator) at the head by default first.
So for bids we want the highest price to come out first. That means the comparator must say “o1 is smaller”
(return < 0) when o1 has a higher price.
Using Long.compare(o2.price, o1.price) achieves that:

Example: o1.price=105, o2.price=100
Long.compare(100, 105) ⇒ -1 ⇒ o1 is “smaller” ⇒ 105 is prioritized

If you flip it to Long.compare(o1.price, o2.price), you get ascending order:

Same example: Long.compare(105, 100) ⇒ +1 ⇒ o1 is “greater” ⇒ 100 is prioritized ✘
That would put lower prices first, which is wrong for bids.

Conclusion:

Long.compare(o2.price, o1.price) ⇒ higher-first (correct for bids)

Long.compare(o1.price, o2.price) ⇒ lower-first (wrong for bids)

*/


public final class OrderBook
{


    // faster lookup of resting orders by id

    private final java.util.Map<Long, Order> idIndex = new java.util.HashMap<>();



    private final PriorityBlockingQueue<Order> bids; // max-heap by price, then ts
    private final PriorityBlockingQueue<Order> asks; //  min-heap by price, then ts

    public OrderBook() {
        bids = new PriorityBlockingQueue<>(1024, (o1,o2) -> {
            /*
             Compare prices. If one is better, return that result.
              If prices are the same,
              the older order (smaller tsNanos) should come first.
            So we compare timestamps in ascending order.
             */
            int byPrice = Long.compare(o2.price , o1.price);
            if (byPrice != 0)
            {
                return byPrice; // prices differ → decide by price
            }
            else
            {
              return Long.compare(o1.tsNanos , o2.tsNanos);
            }

        });

        asks = new PriorityBlockingQueue<>(1024, (o1,o2) -> {

            int byPrice = Long.compare(o1.price , o2.price);
            if (byPrice != 0)
            {
                return byPrice; // prices differ → decide by price
            }
            else
            {
                return Long.compare(o1.tsNanos , o2.tsNanos);
            }
        });
    }

    /*
add(Order incoming)

This is the front door.

It just checks the side:

If it’s a BUY, hand off to addBuy(...).

If it’s a SELL, hand off to addSell(...).

Why? So each path can stay short and easy to read.

     */
    List<Trade> add(Order incoming)
    {
        if (incoming.side == Side.BUY)
        {

            return addBuy(incoming);
        }
        else
        {
            return addSell(incoming);
        }

    };

    // Right now your addBuy/addSell add directly to the queues and don’t index. Change them so that only
    // when the incoming order has leftover qty you call addToBook(incoming) (which adds to the queue and indexes).
private List<Trade> addBuy(Order incoming)
{
    List<Trade>  trades = new ArrayList<>();
    matchBuy(incoming , trades);
    if (incoming.qty > 0)
    {
        addToBook(incoming);   // instead of bids.add(...)
    }

    return trades;
};
    private List<Trade> addSell(Order incoming)
    {
        List<Trade> trades = new ArrayList<>();
        matchSell(incoming, trades);       // try to fill against bids
        if (incoming.qty > 0) {            // leftover? park on asks
            addToBook(incoming);   // instead of asks.add(...)
        }
        return trades;
    };


    private void addToBook(Order o )
    {

        if (o.side == Side.BUY)
        {
            bids.add(o);
        }
        else
        {
            asks.add(o);
        }

        // This order is now resting, index it
        idIndex.put(o.id , o);

    }

    private void removeFromBook(Order o)
    {
        if (o.side == Side.BUY) {
            bids.remove(o);
        }
        else
        {

            asks.remove(o);
        }
        // if we are removing from the book, it shouldn't be indexed as resting
        idIndex.remove(o.id , o);

    }

    /*
cancel(orderId) method tries to remove an order (by its ID) from the order book.
We keep two queues: bids (buy orders) and asks (sell orders).
We search on  both of the queues and remove the first order whose id equals orderId.
It returns true if it removed something from either side; otherwise false.

    *
    * */

    // You remove from the queues, but you must drop the index too if anything was removed.
  boolean cancel(long orderId)
  {
      boolean removed = false;
      boolean x = bids.removeIf(order -> order.id == orderId);
      if (x)
      {
          removed = true;
          idIndex.remove(orderId);
      }

      boolean y = asks.removeIf(o -> o.id == orderId);
      if (y) {
          removed = true;
          idIndex.remove(orderId);
      }

      return removed;

     // This searches both sides (bids and asks), removes any order with that orderId,
      // and returns true if it removed from either side.

  };
  // bestBid() and bestAsk() just show the current top orders on each side of the book.
    //bestBid() → highest-price BUY waiting in the book.
  //bestAsk() → lowest-price SELL waiting in the book.
    //peek() means: look at the element at the front of the queue without removing it.
   // If the queue is empty, peek() returns null.
   // So if there are no buys, bestBid() returns null; if no sells, bestAsk() returns null.
    // Best Bid/Ask (may be null if empty)
    //Why use peek() here?
   // We want to inspect the best price to decide if a new order can match,
    // but we don’t want to delete anything yet. When a trade actually happens,
    // the matching code will explicitly remove or update those orders.

    Order bestBid(){
        return bids.peek();
    };

    Order bestAsk(){
        return asks.peek();
    };

    /*
    *
    *
    *
    * */



    // Keep the index correct during matches. When a resting opposite order is fully consumed, remove it from the index.
    // You already re-add partially filled ones to the queue; just add the idIndex.remove(...) on full fills.
    private void matchBuy(Order buy , List<Trade> out)
    {
        while (buy.qty > 0)  // keep going while this BUY still has quantity
        {
            Order ask = asks.peek(); // look at the BEST (lowest) SELL, but doesn't remove it yet

            if (ask == null || buy.price < ask.price )  // no sells OR prices don't cross → stop
            {
                break;
            } // no cross

            asks.poll();  // remove that best SELL from the queue (we're going to trade with it)
            /*
            Think of two buckets of units that will be exchanged:
            Bucket A = how much the buyer still wants (buy.qty)
           Bucket B = how much the seller still has (ask.qty)
            In one match, you can only trade as many units as both sides can provide/accept.
            That’s exactly the smaller of the two numbers.
            So: long traded = Math.min(buy.qty, ask.qty); means:
          If buyer wants 12 and seller has 5 → you can only trade 5 units(therefore, the seller runs out first).
          If buyer wants 4 and seller has 10 → you can only trade 4 ( therfore , the buyer finishes first).
           If both have 7 → you trade 7.

           After this, we subtract traded from both sides’ remaining quantities.
            * */

            long traded = Math.min(buy.qty , ask.qty); // trade the smaller of the two quantities
            buy.qty -= traded; // reduce remaining on the BUY
            ask.qty -= traded;  // reduce remaining on the SELL

            out.add(new Trade(    // record the trade (a permanent receipt)

                    buy.id,
                    ask.id,
                    ask.price, // trade price = resting SELL’s price
                    traded ,  // how much was traded
                    System.nanoTime() // when the trade happened
                    ));

            if (ask.qty > 0)  // if the SELL still has some left (partial fill)…
            {
                asks.add(ask);  // …put it back in the queue with its remaining qty (priority unchanged)
            }
            else {
                idIndex.remove(ask.id);  // fully done, no longer resting
            }

         }

        }

        private void matchSell(Order sell , List<Trade> out)
        {
            while (sell.qty > 0)
            {
                Order bid = bids.peek();
                if (bid == null || bid.price < sell.price)
                {
                    break;
                }
                bids.poll();
                long traded = Math.min(sell.qty , bid.qty);

                sell.qty -= traded; // reduce remaining on the SELL
                bid.qty -= traded;  // reduce remaining on the BUY

                out.add(new Trade(bid.id , sell.id, bid.price , traded , System.nanoTime()));
                if (bid.qty > 0)
                {
                    bids.add(bid);
                }
                else {
                    idIndex.remove(bid.id);
                }
            }

        }

    public synchronized boolean amend(long orderId, Long newPrice, Long newQty)
    {
        Order o =  idIndex.get(orderId);

        if (o == null)
        {
            return false; // not resting
        }

        // Remove from current price level if price is changing
        boolean reprice = (newPrice != null && newPrice != o.price);

        if (reprice)
        {
            removeFromBook(o);
        }

        if (newQty != null)
        {
            if (newQty <= 0)
            {
                // treat  it as  a full cancel
                removeFromBook(o);
                idIndex.remove(o.id);
                return true;
            }
            o.qty = newQty;
        }
        if (reprice)
        {
            o.price = newPrice;
            // reinsert into appropriate side queue at new price
            addToBook(o); // same path you use for a new resting order
        }
        // idIndex already points at 'o' (mutable object), so no change needed
        return true;

    }

    }

