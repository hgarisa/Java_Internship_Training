package Deep_Threading.Java_Memory_Model.Q3;

/*
*
 Here’s what’s going on, step by step:

You have one shared variable: shared.

Producer runs:

synchronized (LOCK) { shared = 42; }

Acquiring LOCK then releasing it means: its unlock on LOCK will happen-before the next successful lock
 on that same LOCK by any thread.
  That’s the monitor-lock rule.

Consumer runs:

synchronized (LOCK) { System.out.println(shared); }

When it acquires the same LOCK, the happens-before edge guarantees
it sees all writes the producer made inside its critical section — in particular, shared = 42.

So, why do we need LOCK?

It’s the shared monitor object both threads synchronize on.
Using the same object is what creates the lock-based happens-before from producer → consumer,
 which guarantees the consumer reads 42 (not a stale 0)

* */

public class LockHappensBefore {
    static final Object LOCK = new Object();
    static int shared;

    public static void main(String[] args) throws Exception {
        Thread producer = new Thread(() -> {
            synchronized (LOCK) { shared = 42; }
        });
        Thread consumer = new Thread(() -> {
            synchronized (LOCK) { System.out.println(shared); } // must see 42
        });
        producer.start(); producer.join();
        consumer.start(); consumer.join();
    }
}

