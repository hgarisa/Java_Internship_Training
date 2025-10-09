package Deep_Threading.Java_Memory_Model.Q7;

import java.util.concurrent.atomic.AtomicInteger;

/*

 Two counters:

volatile int v

AtomicInteger a

v++ is not a single operation. It’s a read → add → write sequence:
1. read v into a register
2.Adds 1
3. Writes back to v

volatile guarantees visibility & ordering (a write to v is seen by later reads),
but it does not make the three steps atomic.

Interleaving example (lost update):

t1 reads v = 5

t2 reads v = 5

t1 writes 6

t2 writes 6 ← t1’s increment got overwritten

After both threads finish 100,000 increments each, the ideal result is 200,000,
 but due to lost updates you’ll typically print less than 200,000:

incrementAndGet() is atomic. Under the hood it uses a CAS (compare-and-set) loop:

1.read current value (as a volatile read)

2.compute next

3.attempt to swap it in with CAS

4.If someone beat you to it, retry until it succeeds


volatile → visibility/ordering, not atomicity for compound actions like v++.

Use AtomicInteger (or a lock / synchronized) for atomic read-modify-write operations.

If you changed v++ to synchronized block around the increment,
you’d also get 200,000—but with different performance characteristics.


* */
public class VolatileVsAtomic
{
    static volatile int v = 0;
    static AtomicInteger a = new AtomicInteger();
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> { for (int i=0;i<1_000_00;i++) v++; });
        Thread t2 = new Thread(() -> { for (int i=0;i<1_000_00;i++) v++; });

        t1.start(); t2.start(); t1.join(); t2.join();
        System.out.println("volatile v = " + v + " (likely < 200000)");

        a.set(0);
        Thread t3 = new Thread(() -> { for (int i=0;i<1_000_00;i++) a.incrementAndGet(); });
        Thread t4 = new Thread(() -> { for (int i=0;i<1_000_00;i++) a.incrementAndGet(); });

        t3.start(); t4.start(); t3.join(); t4.join();

        System.out.println("AtomicInteger a = " + a.get() + " (== 200000)");



    }

}
