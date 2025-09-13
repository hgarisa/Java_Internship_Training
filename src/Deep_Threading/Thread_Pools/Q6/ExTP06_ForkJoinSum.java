package Deep_Threading.Thread_Pools.Q6;
import java.util.concurrent.*;
import java.util.*;

/**
 * FORK-JOIN RECURSIVE SUM DEMO
 * ----------------------------
 * Goal:
 *  - Show how to use ForkJoinPool with a RecursiveTask to parallelize
 *    a divide-and-conquer sum of a large array.
 *
 * Key idea:
 *  - Split problem (sum of array segment) into two halves recursively.
 *  - If segment is small enough (<= threshold), compute directly (sequential loop).
 *  - Otherwise fork left half (let another worker handle it), compute right half
 *    in current thread, then join the left half’s result.
 *
 * Tuning:
 *  - Threshold determines granularity of tasks:
 *    * Too large: fewer tasks, less parallelism.
 *    * Too small: too many tiny tasks, high overhead.
 */
public class ExTP06_ForkJoinSum
{
    /**
     * RecursiveTask<Long> means:
     *  - Each task returns a Long result (the sum of its assigned subarray).
     */
    static class SumTask extends RecursiveTask<Long> {
        final long[] a;   // the array to sum
        final int lo;     // inclusive start index
        final int hi;     // exclusive end index
        final int THRESH; // minimum chunk size before stopping recursion

        // Constructor captures subarray bounds and threshold
        SumTask(long[] a, int lo, int hi, int THRESH) {
            this.a = a;
            this.lo = lo;
            this.hi = hi;
            this.THRESH = THRESH;
        }

        @Override
        protected Long compute() {
            int len = hi - lo; // length of this segment

            // BASE CASE: segment small enough → compute directly with a loop
            if (len <= THRESH) {
                long s = 0;
                for (int i = lo; i < hi; i++) {
                    s += a[i];
                }
                return s;
            }

            // RECURSIVE CASE: split into two halves
            int mid = lo + len / 2;

            // Left half as a new task (forked for parallel execution)
            SumTask left = new SumTask(a, lo, mid, THRESH);

            // Right half as another task, but computed *directly* in current thread
            SumTask right = new SumTask(a, mid, hi, THRESH);

            // Fork the left task → submit it to ForkJoinPool to run asynchronously
            left.fork();

            // Compute right half immediately (helps avoid thread starvation)
            long r = right.compute();

            // Join waits for left half to finish and returns its sum
            return r + left.join();
        }
    }

    public static void main(String[] args) {
        // Build a large array of 10 million elements (values 0–9 repeating).
        int N = 10_000_000;
        long[] a = new long[N];
        Arrays.setAll(a, i -> i % 10);

        // Use the common ForkJoinPool (shared by default across app).
        ForkJoinPool fjp = ForkJoinPool.commonPool();

        // Choose a threshold: below this size, tasks stop splitting.
        int thr = 50_000;

        // Start timing
        long t = System.currentTimeMillis();

        // Invoke the top-level task to sum the entire array [0, N)
        long sum = fjp.invoke(new SumTask(a, 0, N, thr));

        long ms = System.currentTimeMillis() - t;


        System.out.printf("REPORT_NOTE: ForkJoinSum sum=%d threshold=%d timeMs=%d%n",
                sum, thr, ms);
    }
}
