package Deep_Threading.Thread_Pools.Q7;

import java.util.concurrent.*;
import java.util.*;

/**
 * FORKJOINPOOL VS FIXEDTHREADPOOL (workload skew demo)
 * ----------------------------------------------------
 * Goal:
 *  - Compare how two pool types handle *skewed* workloads:
 *      * A few HEAVY tasks (200 ms each).
 *      * Many LIGHT tasks (1 ms each).
 *
 * Background:
 *  - FixedThreadPool: tasks are placed in a queue and handed to workers.
 *    If a worker happens to grab a heavy task, it stays blocked for ~200 ms,
 *    while others finish quickly. Some workers may go idle waiting.
 *
 *  - ForkJoinPool: uses *work stealing*. When a worker finishes its own tasks,
 *    it can steal tasks from another worker’s queue. This balances the load
 *    more evenly across threads → lower total runtime.
 *
 * What to look for:
 *  - Both pools complete the same work (HEAVY + LIGHT tasks).
 *  - `forkJoinMs` should generally be lower than `fixedMs`
 *    because of better load balancing.
 */
public class ExTP07_FJPWorkSkew
{

    // Number of heavy tasks (simulate expensive work)
    static final int HEAVY = 6;

    // Number of light tasks (simulate cheap work)
    static final int LIGHT = 2000;

    /**
     * Runs the workload (HEAVY + LIGHT tasks) on a given ExecutorService.
     * Returns total elapsed time in milliseconds.
     *
     * FLOW:
     *  1) Record start time.
     *  2) Submit HEAVY tasks (sleep 200 ms each).
     *  3) Submit LIGHT tasks (sleep 1 ms each).
     *  4) Collect all Futures to ensure we wait until *every* task completes.
     *  5) Shutdown pool and return total duration.
     */
    static long runExecutor(ExecutorService es) throws Exception {
        long t = System.currentTimeMillis();

        List<Future<?>> fs = new ArrayList<>();

        // Submit heavy tasks first
        for (int i = 0; i < HEAVY; i++) {
            fs.add(es.submit(() -> sleep(200)));
        }

        // Submit many light tasks
        for (int i = 0; i < LIGHT; i++) {
            fs.add(es.submit(() -> sleep(1)));
        }

        // Wait for all tasks to complete
        for (Future<?> f : fs) {
            f.get(); // block until this task finishes
        }

        // Shutdown executor to release resources
        es.shutdown();

        return System.currentTimeMillis() - t;
    }

    /**
     * Utility method to simulate "work" by sleeping.
     * Heavy tasks sleep longer, light tasks sleep shorter.
     */
    static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ignored) {}
    }

    public static void main(String[] args) throws Exception {
        // Run on a FixedThreadPool with #threads = #cores (min 4)
        long fixed = runExecutor(
                Executors.newFixedThreadPool(Math.max(4, Runtime.getRuntime().availableProcessors()))
        );

        // Run on ForkJoinPool.commonPool() (also sized ~#cores, but with work stealing)
        long fjp = runExecutor(ForkJoinPool.commonPool());

        System.out.printf("REPORT_NOTE: WorkSkew fixedMs=%d forkJoinMs=%d%n", fixed, fjp);
    }

}
