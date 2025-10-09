package Deep_Threading.Java_Memory_Model.Q1;

/*
What the code sets up

You have four shared ints: a, b, x, y (all start at 0).

Thread A runs: a = 1; x = b;

Thread B runs: b = 1; y = a;

You loop many times to catch rare timings.

What each thread means to do

A intends to write a=1, then read b into x.

B intends to write b=1, then read a into y.

The catch: there’s no happens-before

a, b, x, y are plain (not volatile), and there’s no lock.

With no synchronization, the JMM lets the compiler/CPU:

Reorder the two operations inside each thread (because a=1 and x=b are independent; same for B).

Delay visibility of the writes (a=1, b=1) to the other thread (caches, store buffers, etc.).

Why (0,0) can happen

One legal execution:

In Thread A, the read happens first (reordered or just earlier in time):

x = b; sees b still 0 → x=0.

In Thread B, the read also happens first:

y = a; sees a still 0 → y=0.

Later, A does a = 1; and B does b = 1;, but those writes happen after the other thread already read.

Main prints the pair it saw: (x, y) = (0,0).

That’s exactly the “reordering/visibility” scenario the chapter warns about: without a happens-before,
each thread can read the other’s old value.

Other outcomes you might see

(1,0): B read a before A wrote; A read b after B wrote.

(0,1): the mirror image.

(1,1): both reads happened after both writes were visible.

* */
public class ReorderingDemoModified
{
    static int a = 0, b = 0;
    static int x = 0, y = 0;

    public static void main(String[] args) throws Exception {
        int tries = 1_000_000;
        for (int i = 0; i < tries; i++) {
            a = b = x = y = 0;
            Thread t1 = new Thread(() -> { a = 1; x = b; });
            Thread t2 = new Thread(() -> { b = 1; y = a; });
            t1.start(); t2.start(); t1.join(); t2.join();
            if (x == 0 && y == 0) { System.out.println("(0,0) at try " + i); break; }
            else
            {
                System.out.println("( "+ x + "," + y + ")");
            }
        }



    }
}
