package Deep_Threading.Java_Memory_Model.Q4;

import java.util.concurrent.*;
/*

That the code sets up

A simple class BigObj with two fields: x=1, y=2.

Two ways to hand an instance to another thread:

Global variable (global) — no synchronization.

BlockingQueue handoff — internally synchronized*

Part 1 — Unsafe publication (global)

Thread pub = new Thread(() -> global = new BigObj());
Thread use = new Thread(() -> { BigObj o = global; if (o != null) System.out.println(o.x + o.y); });

There’s no happens-before from the writer thread to the reader.
Outcomes are not guaranteed:

Reader might see global == null or see the reference but stale fields if this were more complex .

On many runs you’ll still see 3 because of “lucky” timing/strong-ish hardware—but it’s not reliable.

Part 2 — Safe publication (BlockingQueue)

What is BlockingQueue here?

A thread-safe queue that multiple threads can use to pass objects.
It has built-in locking so you don’t have to write synchronized yourself.

What does new ArrayBlockingQueue<>(1) mean?
It’s a fixed-size queue backed by an array. The 1 is the capacity:
The queue can hold at most one element at a time often called a single-slot “mailbox”

Producer thread

Thread put = new Thread(() -> q.offer(new BigObj()));

offer(e) tries to put e into the queue without blocking.

Because the queue is empty (capacity 1), the offer succeeds immediately and returns true.
If it were full, offer would return false.
The blocking variant is put(e), which waits until space is available.

Consumer thread

Thread take = new Thread(() -> {
  BigObj o = q.take();
  System.out.println(o.x + o.y);
});

take() blocks if the queue is empty, waiting until an element is available.

When the producer has inserted the BigObj,
the consumer unblocks, receives the exact object, and prints o.x + o.y (→ 3).

Why this is safely published

BlockingQueue uses internal synchronization (locks/volatiles).

That means:

A successful put/offer of X happens-before the corresponding take of X.

So the consumer thread is guaranteed to see X’s fields exactly as the producer left them (no stale/half-constructed state).

*
* */
public class PublicationDemo
{
    static class BigObj { int x = 1; int y = 2; } // mutable for demo
    static BigObj global; // UNSAFE path

    public static void main(String[] args) throws Exception {


        // --- Unsafe publication (don’t rely on this) ---
//        Thread pub = new Thread(() -> global = new BigObj());
//        Thread use = new Thread(() -> { BigObj o = global; if (o != null) System.out.println(o.x + o.y); });
//
//        pub.start(); use.start(); pub.join(); use.join();

        // --- Safe publication via BlockingQueue ---

        BlockingQueue<BigObj> q = new ArrayBlockingQueue<>(1);


        Thread put = new Thread(() -> q.offer(new BigObj()));
        Thread take = new Thread(() -> { try { BigObj o = q.take(); System.out.println(o.x + o.y); } catch (Exception ignored) {} });

        put.start(); take.start(); put.join(); take.join();




    }

}
