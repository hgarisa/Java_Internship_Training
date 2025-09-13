package Deep_Threading.Data_Affinity.Q1;

import java.util.concurrent.*;

/**
 * DATA/THREAD AFFINITY DEMO
 * -------------------------
 * Goal:
 *  - Show how assigning each thread a fixed, contiguous "shard" (slice) of an array
 *    reduces cross-core cache transfers and avoids contention.
 *
 * Why this helps:
 *  - When a thread walks a contiguous region, it reuses cache lines (spatial locality).
 *  - Because each thread owns a disjoint region, no two threads touch the same cache line,
 *    so there is no false sharing or coherence ping-pong between cores.
 *
 * What to look for:
 *  - The program is simple and fast; it serves as a baseline for "good locality".
 *  - You can compare later against a "random writes" version to see locality penalties.
 */
public class Q1_AffinityShards
{
    // Total number of array elements to update.
    static final int N = 1_200_000;

    // Number of worker threads. We use at least 4 to utilize multi-core CPUs.
    static final int THREADS = Math.max(4, Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) throws Exception {
        int[] arr = new int[N]; // Shared array that will be partitioned into THREADS shards

        long ms = runPartitioned(arr);

        // Compact line for your report. Consider also printing THREADS and N for context.
        System.out.println("REPORT_NOTE: AffinityShards timeMs=" + ms
                + " threads=" + THREADS + " N=" + N);
    }

    /**
     * FLOW (Partitioned/Affine access pattern):
     *  1) Create a fixed thread pool with THREADS workers.
     *  2) Compute the size of each shard: 'slice = a.length / THREADS'.
     *     - For all threads except the last, the shard is exactly 'slice' wide.
     *     - The last thread takes [lo, a.length) to absorb any remainder (if N % THREADS != 0).
     *  3) Submit THREADS tasks; each task loops over *its own* [lo, hi) range and increments a[i].
     *     - No two tasks touch the same indices -> no sharing of cache lines -> no false sharing.
     *     - Access pattern is linear -> good cache prefetch and spatial locality.
     *  4) Wait for all tasks to finish; return elapsed wall-clock time.
     */
    static long runPartitioned(int[] a) throws Exception {
        // Fixed-size pool so we have exactly THREADS workers.
        ExecutorService pool = Executors.newFixedThreadPool(THREADS);

        // Width of each shard in elements (integer division).
        // Example: N=12, THREADS=5 -> slice=2 (last thread will take the remainder).
        int slice = a.length / THREADS;

        long start = System.currentTimeMillis();

        for (int t = 0; t < THREADS; t++) {
            // Lower bound of this shard: t * slice.
            final int lo = t * slice;

            // Upper bound is normally lo + slice.
            // For the last thread, extend to the end to handle any remainder.
            final int hi = (t == THREADS - 1) ? a.length : lo + slice;

            // Submit the work: this task only touches a[lo..hi-1].
            pool.submit(() -> {
                // HOT LOOP: contiguous, single-writer access to a private region.
                // Because indices are adjacent, the CPU prefetcher pulls in cache lines efficiently.
                for (int i = lo; i < hi; i++) {
                    a[i]++;  // pretend "update per record"
                }
            });
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);

        return System.currentTimeMillis() - start;
    }
}
