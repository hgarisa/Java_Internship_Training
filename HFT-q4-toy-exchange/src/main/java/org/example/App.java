package org.example;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/*
This is just our demo entry point (the program’s starting point).Its job is to do a quick
sanity check that the core pieces (Order, Trade, OrderBook) work.
*
*
*
*
*
* */
public final class App
{

    public static void main(String[] args) throws InterruptedException {

        // Step 2 is basically adding 3 threads around your existing OrderBook.
        OrderBook book = new OrderBook();
        // --- Shared state between threads ---
        AtomicLong mid = new AtomicLong(100); // A single number holding the current market mid-price (we start at 100).
        // AtomicLong = thread-safe primitive. Many threads can read/write it without locks.

        BlockingQueue<Object> inbox = new ArrayBlockingQueue<>(1024); // A thread-safe queue with a fixed capacity (here 1024 slots).
        //The Client thread puts new orders in; the Exchange thread takes them out.
        //If the queue is full, offer() can fail (or put() would be blocked). If it’s empty, take() waits.
        //This is the classic producer → consumer pipe.

   AtomicLong ids = new AtomicLong(1); // unique order ids
   // A thread-safe counter for generating unique order IDs.
        // Each time we need a new ID: ids.getAndIncrement().

        // Why atomic for mid and ids? : Multiple threads touch them; atomics keep updates consistent without heavy locks.

// Why a BlockingQueue? It naturally coordinates two threads at different speeds (client may be faster/slower than exchange) without busy-waiting.

        // So does this mean that the AtomicLong mid value also keeps incrementing like how ids value increments ?
        // Will the mid value always be the same or will it change ?

        // No, mid does not auto-increment like ids . Becuase ids uses getAndIncrement().
        // mid is just an AtomicLong container for the current mid-price. It only changes when you set it.

// So mid will sometimes go up, sometimes down, sometimes stay the same—it’s a tiny random walk.
// It won’t change on its own. only the FEED thread’s mid.set(...) makes it move.
        // The FEED thread updates it.

// ---- FEED thread: Thinks of the FEED thread as a tiny robot that keeps “wiggling” the market price up/down every 200 ms.
//  Why do we need this thread? It simulates the market price moving over time. Our Client will read this mid
// and place BUYs just below it and SELLs just above it, which creates realistic crossings.

Thread feed = new Thread(() -> {
    Random r = new Random(); // We’ll use randomness to decide if price goes up, down, or stays the same.
    while (!Thread.currentThread().isInterrupted())
    {

        long current = mid.get(); // This reads the current mid price from our shared atomic variable.
        // The line below creates a tiny “step” value for the price.
        int step = r.nextInt(3) - 1 ; // r.nextInt(3) means: pick a random integer from 0, 1, or 2.
        // Subtracting - 1 shifts those values down by 1, so you get -1, 0, or +1.
        // Why do this?
        //We want the mid-price to wiggle: sometimes go down by 1 , sometimes stay the same, sometimes go up by 1.
        // So step is one of {-1, 0, +1}. Then we add it to the current price.
        long next = Math.max(1 , current + step ); // This line just makes the next price never drop below 1.
        // Math.max(1, X) = Choosing the larger number of 1 and X.
        // So if current + step ever goes below 1, we clamp it back up to 1.
        mid.set(next); // Publish the new price so other threads (Client/Exchange) see it.

        try
        {
            Thread.sleep(200);
        }catch (InterruptedException e)
        {
            return;
        }
        // Pause for ~200 ms to avoid spamming updates. If sleep is interrupted, exit the thread cleanly.

    }

} , "FEED");


// The Client Thread is just a tiny “trader bot” that keeps placing orders into the queue for the EXCHANGE to process
// Make the client occasionally send a cancel. Keep your existing new-order logic.
// After you send an order, occasionally enqueue a cancel for a recent id
// (it’s okay if it sometimes doesn’t exist—then you’ll see a cancel reject, which is realistic).

Thread client = new Thread(() -> {
boolean buyNext = true; // we’ll alternate between BUY and SELL each time.
    java.util.Random r = new java.util.Random();
    long lastID = 0;
    int loop = 0;

while (!Thread.currentThread().isInterrupted())
{
    long now = System.nanoTime();
    long m = mid.get();
    long id = ids.getAndIncrement();

    if(buyNext)
    {
        long price = Math.max(1 , m -1);   // buy slightly below mid
        inbox.offer(new Order(id , Side.BUY , price  , 5 , now)); // create a BUY of qty 5 and offer it to the queue.
    }
    else
    {
        long price = m + 1 ;  // sell slightly above mid
        inbox.offer(new Order(id , Side.SELL , price  , 4 , now)); // Create a SELL of qty 4 and offer i
    }

    buyNext = !buyNext;
    lastID = id;
    loop++;

    // Every 3rd loop, try to cancel a recent id (id or id-1)
    if (loop % 3 == 0)
    {

        long target = Math.max(1,  lastID - r.nextInt(2));
        //  r.nextInt(2) : Returns a random int from {0, 1} (never 2). So it’s “coin flip”: 0 or 1.
        // Most recent order = the latest order ID issued so far = lastId
        // Previous order = the one just before the latest = lastId - 1.
        //  They aren’t the same thing. If your IDs go … 13, 14, 15, then:
        // Most recent = 15 and Previous = 14

        // Math.max(1, …) just prevents going below 1 when lastId is 1.

        // Does the actual number (15, 14…) matter?

        // No. Whether lastId = 15 or lastId = 14, the logic is the same:
        // Random 0 → cancel the most recent (lastId)
        // Random 1 → cancel the previous (lastId - 1, clamped at 1)

        // It doesn’t “only cancel when lastId = 1”. The clamp only protects the lower bound for very early IDs.

        /*
        One more nuance
       Issuing a cancel for an ID doesn’t guarantee that something gets removed:
        If that order is already fully filled or already canceled, the cancel will be a no-op.
        If it’s still resting in the book, the cancel removes it

        * */

        inbox.offer(new CancelRequest(System.nanoTime() , target));


    }
    // Every 5th loop, try to amend a recent id (id, id-1, or id-2)
    if (loop % 5 == 0) {
        long target = Math.max(1, lastID - r.nextInt(3));
        if (r.nextBoolean()) {

            // What does r.nextBoolean() do? It returns a a random boolean:
            // true about 50% of the time, false about 50% of the time.
            // You’re using it to randomly to choose which kind of amendment to send:
            // true → send a qty change (newQty = 2L, no price change)
            // false → send a price change (newPrice = mid ± 1, no qty change)
            // So each amend cycle you have a coin-flip between “re-qty” and “re-price”.

            // change qty example
            inbox.offer(new AmendRequest(System.nanoTime(), target, null, 2L));
        } else {
            // reprice example: nudge around mid
            long delta;
            if (r.nextBoolean()) {
                delta = 1;   // up
            } else {
                delta = -1;  // down
            }
            long newPx = Math.max(1, mid.get() + delta);

            inbox.offer(new AmendRequest(System.nanoTime(), target, newPx, null));
        }
    }


    try {
        Thread.sleep(500); // It waits for wait ~0.5s so that  we don’t spam updates.
    }
    catch (InterruptedException e)
    {
        return;
    }
}
// The offer(...) adds to the queue without blocking; if the queue were full, it would return false.
    //The EXCHANGE thread is the only one that takes orders from inbox and calls book.add(...).

} , "CLIENT");


// Create the bus and one viewer subscription
        MarketDataBus mdbus = new MarketDataBus();
        var mdqueue = mdbus.subscribe(4096);

        Thread viewer =  new Thread(() -> {

            while (!Thread.currentThread().isInterrupted())
            {
                try
                {

                    Object ev = mdqueue.take();
                    if (ev instanceof TradeEvent te)
                    {

                        System.out.println("[ MD Trade] " + te.trade());
                    }
                    else if (ev instanceof TopOfBook tob)
                    {
                        System.out.println(" [ MD TOP ] mid =  " + tob.mid()

                                +  " bid=" + tob.bid()
                                + " ask=" + tob.ask());
                    }
                    else if (ev instanceof CancelEvent ce) {
                        System.out.println("[ MD Cancel ] id=" + ce.orderId() + " ok=" + ce.ok()
                                + (ce.ok() ? "" : " (" + ce.reason() + ")"));
                    }
                    else if (ev instanceof AmendEvent ae) {
                        System.out.println("[ MD Amend ] id=" + ae.orderId() + " ok=" + ae.ok()
                                + (ae.ok() ? "" : " (" + ae.reason() + ")"));
                    }


                }
                catch (InterruptedException e)
                {
                    return;
                }
            }

        } , "MD-View" );



RiskEngine  risk = new RiskEngine();

// Think of the EXCHANGE thread as the “mini exchange engine.”
// Teach the exchange to handle both message types
//

Thread exchange = new Thread(() -> {

    long lastPrint = 0;  // <— declare once, before the loop
    while (!Thread.currentThread().isInterrupted())
    {
        try {

            Object msg =  inbox.take();  // .take() waits (blocks) until an order is available, then returns it
            // If the queue is empty, the thread pauses here instead of spinning CPU.

            if (msg instanceof Order o)
            {

                // (1) Risk Check
                RiskEngine.Result rr = risk.check(o , mid.get());
                if (!rr.ok)
                {
                    System.out.println("Reject ID =  " + o.id + " side : " + o.side + " px= " + o.price +  " qty " + o.qty + " reason " + rr.reason);
                    continue;
                }

                // (2) Match
                List<Trade> trades = book.add(o); // Send the order into the OrderBook to try immediate matches; returns any Trades created now.

                // (3) publish trades
                for (Trade t : trades)
                {
                    mdbus.publish(new TradeEvent(System.nanoTime() , t));
                }


            } else if ( msg instanceof CancelRequest c)
            {
                boolean ok = book.cancel(c.orderId());
                String reason = ok ? null : "not_found";
                mdbus.publish(new CancelEvent(System.nanoTime() , c.orderId() , ok , reason));

            }
            else if (msg instanceof AmendRequest a) {
                boolean ok = book.amend(a.orderId(), a.newPrice(), a.newQty());
                String reason = ok ? null : "not_resting";
                mdbus.publish(new AmendEvent(System.nanoTime(), a.orderId(), ok, reason));
            }




            // 4) Publish TOP once per second
            long nowMs = System.currentTimeMillis();
            if (nowMs - lastPrint >= 1000)
            {

                mdbus.publish(new TopOfBook(
                        System.nanoTime(),
                        mid.get(),
                        book.bestBid(),
                        book.bestAsk()
                ));
                lastPrint = nowMs; // update marker
            }

        }
        catch (InterruptedException e)
        {
            return;
        }

    }

} , "EXCHANGE");

// Note : We do not run the risk checks for cancel requests - exchanges typically allow cancels freely (subject to session/state).



// What this code is for is that ir reads orders from a queue, run the matching logic, and log results.
        // Why do we need it ? So that keeping all book updates in one thread avoids tricky concurrency bugs (no two threads modifying bids/asks at the same time).

// --- Start them ---
        feed.start();
        client.start();
        viewer.start();
        exchange.start();

        // --- Let demo run for ~10s, then stop ---
        // The interrupt() method sends an interrupt signal to each worker thread.
        // Why we use interrupt ?
        //  It’s the standard, safe way to ask a thread to stop what it’s doing and exit.
        //

        Thread.sleep(10_000);
        feed.interrupt();
        client.interrupt();
        viewer.interrupt();
        exchange.interrupt();


        // Big picture (why this design)
        // The FEED thread moves a simple “market mid” up/down.
        // The Client thread reads that mid-price value and produces orders (BUY a tick below, SELL a tick above).
        // The EXCHANGE thread is the only thread that consumes orders and updates the OrderBook (so no race conditions).

        // The flow (who talks to who)
      // FEED every ~200ms: Reads mid, wiggles it by −1/0/+1, writes it back (mid.set(next)).
        // CLIENT every ~500ms:
        //Reads mid and gets a new id.
        //Alternates:
        //BUY at mid−1 (qty 5) → puts to inbox.
        //SELL at mid+1 (qty 4) → puts to inbox.

        // The Exchange thread inbox.take() (blocks until an order arrives).
        // It calls book.add(order):
        // If it crosses the opposite side (buy.price ≥ best ask OR best bid ≥ sell.price), it creates Trade(s) and reduces qty.
        // Any leftover qty is rested on bids/asks.
        // Prints any TRADES immediately.
        // About once per second prints TOP mid=… bid=… ask=… (snapshots via peek()).

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // Step 1 : Quick Sanity Check
//        OrderBook book = new OrderBook(); // Create a fresh, empty order book (two queues: bids and asks).
//        long now = System.nanoTime(); // Grab a timestamp to stamp the test orders.
//        // quick sanity: add crossing orders
//        List<Trade> t1 = book.add(new Order(1, Side.BUY, 100, 10, now)); // Adds a buy for price 100,
//        // qty 10. No sells yet, so it will sit.
//        List<Trade> t2 = book.add(new Order(2, Side.SELL, 99, 7, now)); // Adds a sell at 99,
//        // Because 100 (buy) ≥ 99 (sell), they cross and trade immediately.
//        //Result: a Trade for qty min(10,7)=7 at price 99.
//        // The buy has 3 left (10–7). The sell is fully filled (7–7 = 0).
//
//        System.out.println(" Trades after cross : " +  t1); // t1 is the list of trades from the first add ((the BUY) . . No SELL existed then → []
//        System.out.println(" Trades after cross : " +  t2); // t2 is the trades from the second add (the SELL). It matched the resting buy → one Trade
//        System.out.println("BestBid = " +  book.bestBid()); // BestBid= shows the highest BUY still resting (should be the buy with qty 3 @ 100).
//        System.out.println("BestAsk = " +  book.bestAsk()); // BestAsk= shows the lowest SELL still resting (likely null now because that sell was fully filled)

    }



}
