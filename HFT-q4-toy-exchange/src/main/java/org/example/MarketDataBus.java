package org.example;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class MarketDataBus
{
    //  List<BlockingQueue<Object>> -> it’s a list of subscriber inboxes.
    // Each subscriber gets its own BlockingQueue<Object> where we drop events for that subscriber.
    // Why BlockingQueue<Object>? BlockingQueue: thread-safe queue; a slow subscriber can take()
    // and  be blocked until data comes, without busy-waiting.
    // Object: we want to send different event types (e.g., TradeEvent, TopOfBook),
    // therefore that is why we use the common supertype Object.
    // In the viewer we do instanceof to tell them apart.

    private final List<BlockingQueue<Object>> subs = new CopyOnWriteArrayList<>();

    /** Create and register a subscriber queue. */

    public BlockingQueue<Object> subscribe(int capacity)
    {
        BlockingQueue<Object> q  = new ArrayBlockingQueue<>(capacity);
      subs.add(q);
      return q;

    };



    // Think of subs as a mailing list of individual mailboxes (queues).
    // When we publish, we loop that list and drop a copy of the event into each mailbox.
    // When we say “mailing,” , we mean the loop inside publish(...):

    public void publish( Object event )
    {
        for (BlockingQueue<Object> q : subs)
        {
            q.offer(event);

        }

    }
    // We walk through the list of subscribers (subs) and drop a copy of the same event into each subscriber’s queue—
    // like putting the same letter into many mailboxes.

    // “Publishing” means calling the method " mdBus.publish(event) " inside the Exchange thread. That method:
    // Looks at the list of subscriber queues (subs)
    // Offers the event into each queue (non-blocking)
    //  So “publish” = fan out one event to all subscribers’ inboxes.

    // "private final List<BlockingQueue<Object>> subs = new CopyOnWriteArrayList<>(); "
    // so is there a queue inside a queue ? in this code
    // The answer is no . What we have is : a list (subs) …that contains multiple queues.
// Think : subs = [ queueA, queueB, queueC, ... ]
    // Each element of the list is a BlockingQueue<Object>
  // Each queue then contains events (like TradeEvent, TopOfBook) — not other queues
    // So the structure is:

   /*
   List
 ├─ BlockingQueue<Object>  (subscriber 1’s inbox)
 │    ├─ event
 │    ├─ event
 │    └─ ...
 ├─ BlockingQueue<Object>  (subscriber 2’s inbox)
 │    ├─ event
 │    └─ ...
 └─ BlockingQueue<Object>  (subscriber 3’s inbox)
        └─ ...

        // Publishing = iterate the list and offer(event) into each queue.

    */


    // What do we “do” in publishing?

    // Input: one event (e.g., new TradeEvent(...) or new TopOfBook(...))
    // Action: iterate over subs and offer(event) to every queue
    // Output: each subscriber can later take() the event from its own queue

    // “Add/removing subscribers” : This means adding/removing subscriber queues to/from the subs list,
    // not adding/removing items inside a single queue.
    // For example : Add a subscriber = create a queue and put it into the  subs list:

    // BlockingQueue<Object> q = mdBus.subscribe(4096); // internally does subs.add(q)

    // Remove a subscriber (if you add this later) would be something like:

    //subs.remove(q); // stop sending events to that subscriber

    // It does not mean adding/removing the actual events.
    // Events are added to the queues during publish;
    // subscribers remove them by calling take().

    // Why each subscriber gets its own queue?
    //So a slow subscriber doesn’t block or delay a fast one. Each processes at its own speed.


    // Why this design?
    // CopyOnWriteArrayList lets subscribers be added safely at runtime.
    // Each subscriber gets its own queue → a slow subscriber won’t block the Exchange.

}
