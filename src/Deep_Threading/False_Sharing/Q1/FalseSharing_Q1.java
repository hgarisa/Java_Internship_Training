package Deep_Threading.False_Sharing.Q1;

import java.util.concurrent.atomic.LongAdder;

/**
 * FALSE SHARING DEMO (high-level)
 * -------------------------------
 * We run three micro-benchmarks that increment two independent counters in parallel:
 *
 * 1) Adjacent fields (Adj): 'a' and 'b' are right next to each other in memory
 *    -> likely to sit in the same CPU cache line (e.g., 64 bytes) -> false sharing risk.
 *
 * 2) Padded fields (Pad): we insert dummy long fields between 'a' and 'b'
 *    -> increases the chance they end up on different cache lines -> less interference.
 *
 * 3) LongAdder baseline: uses internal striping to reduce contention/false sharing.
 *
 * Why "false" sharing?
 * - Even though the two threads touch different variables, if those variables live
 *   on the same cache line, each core keeps invalidating the other's cache line,
 *   forcing expensive coherence traffic.
 *
 * What to look for:
 * - 'adjacentMs' will typically be slower than 'paddedMs' and 'longAdderMs'.
 * - Exact numbers depend on CPU, JVM, OS; run a few times to see the pattern.
 */
public class FalseSharing_Q1
{
    // How many increments each thread performs (big enough to amplify differences)
    static final int ROUNDS = 40_000_000;

    /**
     * Adjacent fields (likely same cache line).
     * 'volatile' gives us visibility (prevents the JIT from eliminating the writes),
     * but it does not make ++ atomic (we don't need atomicity here because each field
     * is written by only one thread).
     */
    static class Adj { volatile long a, b; }

    /**
     * Padded fields to push 'a' and 'b' far apart.
     * Each long is 8 bytes, so 7 padding longs ~ 56 bytes around the hot field.
     * Together with object headers, this strongly encourages separate cache lines.
     */
    static class Pad {
        volatile long a;
        long p1,p2,p3,p4,p5,p6,p7;  // left padding around 'a'
        volatile long b;
        long q1,q2,q3,q4,q5,q6,q7;  // right padding around 'b'
    }

    public static void main(String[] args) throws Exception {
        // Run the three scenarios and print a compact line you can paste in your report.
        System.out.println(
                "REPORT_NOTE: FalseSharing " +
                        "adjacentMs=" + timeAdjacent() +
                        " paddedMs=" + timePadded() +
                        " longAdderMs=" + timeLongAdder()
        );
        // Interpretation:
        // - If adjacentMs >> paddedMs, you've observed false sharing.
        // - LongAdder is a common production alternative for hot counters.
    }

    /**
     * FLOW (Adjacent):
     * 1) Make one Adj object with two volatile counters (a, b).
     * 2) Start two threads:
     *    - t1 increments x.a ROUNDS times.
     *    - t2 increments x.b ROUNDS times.
     *    Because 'a' and 'b' are adjacent, both cores likely fight over the same
     *    cache line -> invalidations -> slower overall time.
     * 3) Wait for both threads to finish (join) and return elapsed ms.
     */
    static long timeAdjacent() throws Exception {
        Adj x = new Adj();
        long t = System.currentTimeMillis();

        Thread t1 = new Thread(() -> { for (int i = 0; i < ROUNDS; i++) x.a++; });
        Thread t2 = new Thread(() -> { for (int i = 0; i < ROUNDS; i++) x.b++; });

        t1.start(); t2.start();
        t1.join();  t2.join();

        return System.currentTimeMillis() - t;
    }

    /**
     * FLOW (Padded):
     * Same as above, but 'a' and 'b' are separated by padding longs,
     * so each thread likely updates a different cache line.
     * Result: fewer coherence invalidations -> typically faster time.
     */
    static long timePadded() throws Exception {
        Pad x = new Pad();
        long t = System.currentTimeMillis();

        Thread t1 = new Thread(() -> { for (int i = 0; i < ROUNDS; i++) x.a++; });
        Thread t2 = new Thread(() -> { for (int i = 0; i < ROUNDS; i++) x.b++; });

        t1.start(); t2.start();
        t1.join();  t2.join();

        return System.currentTimeMillis() - t;
    }

    /**
     * FLOW (LongAdder baseline):
     * - LongAdder spreads updates across internal "cells" to reduce contention
     *   and cache-line ping-pong when many threads increment the same logical counter.
     * - Here we use two separate LongAdders (a, b), one per thread, mimicking the
     *   separated counters case but with a production-friendly class.
     * - Expect this to be competitive with or faster than the padded version.
     */
    static long timeLongAdder() throws Exception {
        LongAdder a = new LongAdder(), b = new LongAdder();
        long t = System.currentTimeMillis();

        Thread t1 = new Thread(() -> { for (int i = 0; i < ROUNDS; i++) a.increment(); });
        Thread t2 = new Thread(() -> { for (int i = 0; i < ROUNDS; i++) b.increment(); });

        t1.start(); t2.start();
        t1.join();  t2.join();

        return System.currentTimeMillis() - t;
    }
}
