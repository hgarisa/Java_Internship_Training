package Deep_Threading.Thread_Contention.Q1;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.*;

/**
 * THREAD CONTENTION DEMO (Single global lock vs. Striped locks)
 * -------------------------------------------------------------
 * Goal:
 *  - Simulate many threads incrementing per-"bucket" counters (think: per symbol/shard).
 *  - Case 1 (single lock): all threads must take ONE global lock -> heavy contention.
 *  - Case 2 (striped locks): each bucket has its own lock -> less time spent waiting.
 *
 * What to look for:
 *  - 'stripedMs' should usually be lower than 'singleMs' (often much lower),
 *    because threads can update different buckets in parallel.
 *  - 'speedup' tells you how many times faster the striped version is.
 */
public class Ex01_Contention {

    // How many worker threads to run.
    // Using at least 4 helps show visible contention on multi-core CPUs.
    static final int THREADS = Math.max(4, Runtime.getRuntime().availableProcessors());

    // How much work each thread does (number of increments).
    // Increase if results look noisy; decrease if it takes too long.
    static final int ORDERS_PER_THREAD = 250_000;

    // Number of independent counters (buckets). Think: per-symbol counters in a trading system.
    // We pick a power-of-two-ish number so hashing/mod operations are cheap (not required though).
    static final int BUCKETS = 1024;

    public static void main(String[] args) throws Exception {
        // Run both designs and measure wall-clock time in milliseconds.
        long singleMs = runSingleLock();
        long stripedMs = runStriped();

        // Simple ratio to summarize improvement (“how many times faster”).
        double speedup = (double) singleMs / Math.max(1, stripedMs);

        // Compact line you can paste into your report
        System.out.printf(
                "REPORT_NOTE: Contention single=%dms striped=%dms speedup=%.2fx%n",
                singleMs, stripedMs, speedup
        );
    }

    /**
     * CASE 1: Single global lock.
     * FLOW:
     *  1) Create one ReentrantLock for ALL buckets.
     *  2) Start THREADS workers; each worker does ORDERS_PER_THREAD increments.
     *  3) Each increment:
     *       - pick a random bucket
     *       - acquire the ONE global lock
     *       - increment counts[b]
     *       - release the lock
     *     => Only one thread can update ANY bucket at a time -> high contention.
     *  4) Wait for all threads to finish and return the elapsed time.
     */
    static long runSingleLock() throws Exception {
        ReentrantLock lock = new ReentrantLock(); // single shared lock
        int[] counts = new int[BUCKETS];          // per-bucket counters

        ExecutorService pool = Executors.newFixedThreadPool(THREADS);
        long start = System.currentTimeMillis();

        for (int t = 0; t < THREADS; t++) {
            pool.submit(() -> {
                ThreadLocalRandom r = ThreadLocalRandom.current();
                for (int i = 0; i < ORDERS_PER_THREAD; i++) {
                    int b = r.nextInt(BUCKETS); // choose a random bucket
                    lock.lock();                // acquire the ONE global lock
                    try {
                        counts[b]++;            // critical section (very small)
                    } finally {
                        lock.unlock();          // release so others can proceed
                    }
                }
            });
        }

        // Stop accepting new tasks and wait for all to complete
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);

        // Total time spent doing the work under a single, contended lock
        return System.currentTimeMillis() - start;
    }

    /**
     * CASE 2: Striped locks (one lock per bucket).
     * FLOW:
     *  1) Create an array of ReentrantLock of size BUCKETS (lock[i] protects counts[i]).
     *  2) Start THREADS workers; each worker does ORDERS_PER_THREAD increments.
     *  3) Each increment:
     *       - pick a random bucket
     *       - lock ONLY that bucket's lock
     *       - increment counts[b]
     *       - unlock that bucket
     *     => Different buckets can be updated in parallel by different threads.
     *        Contention only happens when two threads pick the SAME bucket at the same time.
     *  4) Wait for all threads and return the elapsed time.
     *
     * Notes:
     *  - Stripes reduce contention dramatically when updates are spread across buckets.
     *  - If your workload is very skewed (hot bucket), that one lock may still contend.
     */
    static long runStriped() throws Exception {
        // One lock per bucket -> fine-grained locking
        ReentrantLock[] locks = new ReentrantLock[BUCKETS];
        Arrays.setAll(locks, i -> new ReentrantLock());

        int[] counts = new int[BUCKETS];

        ExecutorService pool = Executors.newFixedThreadPool(THREADS);
        long start = System.currentTimeMillis();

        for (int t = 0; t < THREADS; t++) {
            pool.submit(() -> {
                ThreadLocalRandom r = ThreadLocalRandom.current();
                for (int i = 0; i < ORDERS_PER_THREAD; i++) {
                    int b = r.nextInt(BUCKETS);  // choose bucket
                    ReentrantLock L = locks[b];  // take that bucket's lock
                    L.lock();
                    try {
                        counts[b]++;             // update only that bucket's counter
                    } finally {
                        L.unlock();              // release only that bucket
                    }
                }
            });
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);

        return System.currentTimeMillis() - start;
    }
}
