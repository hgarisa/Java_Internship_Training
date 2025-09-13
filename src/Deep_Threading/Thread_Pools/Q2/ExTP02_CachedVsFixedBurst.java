package Deep_Threading.Thread_Pools.Q2;

import java.util.concurrent.*;

/*
 * CACHED vs FIXED THREAD POOL (bursty, tiny tasks)
 * ------------------------------------------------
 * Goal:
 *  - Submit a large burst of *very short* tasks and compare total completion time
 *    between:
 *      (1) CachedThreadPool: elastic; can create many threads quickly to absorb bursts.
 *      (2) FixedThreadPool : limited to N worker threads; excess tasks are queued.
 *
 * Intuition:
 *  - With ultra-short tasks, queueing + context switching overheads dominate.
 *  - A cached pool may spin up many threads so more tasks complete in parallel,
 *    potentially finishing the burst sooner (but at the cost of more thread churn).
 *  - A fixed pool caps concurrency; tasks beyond the cap wait in the queue,
 *    which can make the burst finish later even though it uses fewer threads.
 *
 * Caveats:
 *  - This is a micro-benchmark; results depend on CPU, OS, JVM, and TASKS size.
 *  - For *CPU-bound* or *long-running* tasks, a fixed pool sized ~#cores is often better.
 *  - For *short/IO-ish/bursty* tasks, elastic pools can reduce queueing delay.
 */
public class ExTP02_CachedVsFixedBurst
{
    // Total number of tiny tasks to submit in one burst.
    static final int TASKS = 20_000;

    // The "work" each task does: effectively nothing (returns immediately).
    // We keep it empty on purpose to highlight submission/queueing/scheduling overheads.
    static final Runnable tiny = () -> {}; // super-short run

    /**
     * Measure end-to-end time to submit TASKS tiny jobs and wait for all to complete
     * on the given ExecutorService.
     *
     * FLOW:
     *  1) Start wall-clock timer.
     *  2) Submit TASKS callables/runnables and store their Futures.
     *     - For a cached pool: may create many threads quickly (less queueing).
     *     - For a fixed pool : only N threads run; the rest queue up.
     *  3) Future#get() for each task to ensure we wait until *all* tiny tasks finish.
     *  4) Shutdown the pool and return elapsed time in ms.
     */
    static long time(ExecutorService es) throws Exception {
        long t = System.currentTimeMillis();

        // Keep references so we can join on every submitted task.
        Future<?>[] fs = new Future<?>[TASKS];

        // Submit all tasks in a tight loop (a single large burst of work).
        for (int i = 0; i < TASKS; i++) {
            fs[i] = es.submit(tiny);
        }

        // Wait for all tasks to complete; this includes any queueing/scheduling delays.
        for (Future<?> f : fs) {
            f.get(); // blocks until that tiny task has actually run
        }

        // Always shutdown executors to release threads/resources.
        es.shutdown();


        return System.currentTimeMillis() - t;
    }

    public static void main(String[] args) throws Exception {
        // Create a cached pool:
        // - Unbounded max threads (practically), creates threads on demand,
        //   reuses idle ones, and reaps threads that stay idle for ~60s.
        long cached = time(Executors.newCachedThreadPool());

        // Create a fixed pool sized around available CPUs (min 4 here).
        // - Concurrency is capped; excess tasks will sit in the queue until a worker is free.
        long fixed  = time(Executors.newFixedThreadPool(
                Math.max(4, Runtime.getRuntime().availableProcessors())));


        System.out.printf("REPORT_NOTE: CachedVsFixed cachedMs=%d fixedMs=%d%n", cached, fixed);
    }
}
