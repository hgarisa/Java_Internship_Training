package Deep_Threading.Thread_Pools.Q1;
import java.util.concurrent.*;

/**
 * FIXED THREAD POOL vs SINGLE THREAD (CPU-bound)
 * ----------------------------------------------
 * What this measures:
 *  - We perform the same amount of pure CPU work in two ways:
 *    (1) Single-threaded (all work in one task on one thread)
 *    (2) Parallel on a fixed-size pool (~=#cores), splitting the work across many tasks
 *
 * Expectation:
 *  - For CPU-bound work (no I/O), parallelizing up to the number of available CPU cores
 *    should reduce total wall-clock time. The max speedup is bounded by #cores and overheads.
 *
 * Notes for better measurements (optional):
 *  - Run the program a couple of times to let the JIT warm up.
 *  - You could use System.nanoTime() for higher timing precision; millis is fine for this scale.
 *  - Adjust TASKS and WORK to make the difference clearer on your machine.
 */
public class ExTP01_FixedVsSingle
{
    // Number of tasks used in the fixed-pool case (and also used to scale the single-threaded work).
    // Using ~8x cores creates enough parallel tasks to saturate the pool without creating too much overhead.
    static final int TASKS = 8 * Math.max(1, Runtime.getRuntime().availableProcessors());

    // Work size per task (the hash loop length). Bigger WORK => heavier CPU usage per task.
    static final int WORK  = 2_000_000;

    /**
     * Run the entire workload on a single thread.
     * FLOW:
     *  1) Create a single-threaded executor (only 1 worker thread).
     *  2) Submit ONE task that does ALL the work: WORK * TASKS iterations.
     *     (So the total work equals the sum of all parallel tasks in runFixed().)
     *  3) Wait for completion with Future#get() to ensure timing includes the actual work.
     *  4) Shutdown the executor and return elapsed wall-clock time.
     */
    static long runSingle() throws Exception {
        ExecutorService one = Executors.newSingleThreadExecutor();
        long t = System.currentTimeMillis();

        // Submit a single big task that performs the whole workload serially.
        Future<?> f = one.submit(() -> burn(WORK * TASKS));

        // Wait until that task really finishes; this blocks the caller until completion or exception.
        f.get();

        // Always shutdown executors to release threads/resources.
        one.shutdown();

        // Return duration in milliseconds.
        return System.currentTimeMillis() - t;
    }

    /**
     * Run the same total workload on a fixed-size pool.
     * FLOW:
     *  1) Choose pool size n ~= number of CPU cores (parallelism level).
     *  2) Create a fixed thread pool with n workers.
     *  3) Submit TASKS separate tasks; each task runs burn(WORK) i.e., a fraction of the total work.
     *  4) Wait for ALL tasks to complete (Future#get() for each).
     *  5) Shutdown and return elapsed wall-clock time.
     */
    static long runFixed() throws Exception {
        int n = Math.max(2, Runtime.getRuntime().availableProcessors());
        ExecutorService pool = Executors.newFixedThreadPool(n);
        long t = System.currentTimeMillis();

        // Submit many small tasks so the pool can run them in parallel.
        Future<?>[] fs = new Future<?>[TASKS];
        for (int i = 0; i < TASKS; i++) {
            fs[i] = pool.submit(() -> burn(WORK));
        }

        // Wait for every task to finish, ensuring the measured time includes all work.
        for (Future<?> f : fs) {
            f.get();
        }

        pool.shutdown();
        return System.currentTimeMillis() - t;
    }

    /**
     * Pure CPU "burn" function.
     * - Performs a deterministic integer-mixing loop to keep the CPU busy.
     * - The tiny 'if (x==42) System.out.println();' is a harmless guard to prevent
     *   the JIT from proving the loop has no externally visible effect and removing it.
     *   (It virtually never prints; the condition is almost never true.)
     */
    static void burn(int n) {
        int x = 0;
        for (int i = 0; i < n; i++) {
            x = (x * 31) ^ i; // simple integer mixing; cheap but keeps ALUs busy
        }
        if (x == 42) System.out.println(); // side-effect to defeat aggressive dead-code elimination
    }

    /**
     * Main driver:
     *  - Measures single-threaded time vs fixed-pool parallel time, then prints the speedup.
     *  - Speedup = singleMs / fixedMs (values > 1.0 indicate improvement from parallelism).
     */
    public static void main(String[] args) throws Exception {
        long single = runSingle();
        long fixed  = runFixed();

        System.out.printf(
                "REPORT_NOTE: FixedVsSingle singleMs=%d fixedMs=%d speedup=%.2fx%n",
                single, fixed, (double) single / Math.max(1, fixed)
        );
    }
}
