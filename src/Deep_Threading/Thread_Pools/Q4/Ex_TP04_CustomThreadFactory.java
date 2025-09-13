package Deep_Threading.Thread_Pools.Q4;

import java.util.concurrent.*;

/**
 * CUSTOM THREAD FACTORY DEMO
 * --------------------------
 * Goal:
 *  - Show how to provide a custom ThreadFactory to a ThreadPoolExecutor.
 *  - Benefits:
 *      * Give threads meaningful names (useful in logs / debuggers).
 *      * Set custom priority (here just NORM_PRIORITY, but could be higher/lower).
 *      * Attach an UncaughtExceptionHandler to log uncaught errors in worker threads.
 *
 * Flow:
 *  1) Create a ThreadFactory (lambda) that builds threads with:
 *        - a unique name "wrk-<timestamp>"
 *        - normal priority
 *        - custom uncaught-exception handler (prints error to console)
 *  2) Create a ThreadPoolExecutor using that ThreadFactory.
 *  3) Submit two tasks:
 *        - ok task: does nothing, completes normally
 *        - boom task: throws RuntimeException
 *  4) Observe:
 *        - ok.get() returns normally
 *        - boom.get() throws ExecutionException (caught in try/catch)
 *        - uncaught handler prints error info to console
 *  5) Shutdown pool and print final REPORT_NOTE.
 */

public class Ex_TP04_CustomThreadFactory
{
    public static void main(String[] args) throws Exception {

        // Custom ThreadFactory: creates threads with name + priority + exception handler
        ThreadFactory tf = r -> {
            // r = the task to run inside the thread
            Thread t = new Thread(r, "wrk-" + System.nanoTime()); // custom name
            t.setPriority(Thread.NORM_PRIORITY); // you could lower/raise if needed

            // UncaughtExceptionHandler: if a task throws an exception not caught inside run()
            t.setUncaughtExceptionHandler((th, ex) ->
                    System.out.println("REPORT_NOTE: uncaught in " + th.getName() + ": " + ex)
            );

            return t;
        };

        // Create a ThreadPoolExecutor manually (instead of Executors.* helper)
        ExecutorService pool = new ThreadPoolExecutor(
                2,                        // core pool size
                4,                        // max pool size
                30, TimeUnit.SECONDS,     // idle timeout
                new SynchronousQueue<>(), // queue: tasks are handed off directly to threads
                tf                        // our custom ThreadFactory
        );

        // Submit one normal task: completes fine
        Future<?> ok = pool.submit(() -> {});

        // Submit one failing task: throws RuntimeException
        Future<?> boom = pool.submit(() -> { throw new RuntimeException("boom"); });

        // Wait for ok task to complete successfully
        ok.get();

        // Wait for boom task; .get() wraps the RuntimeException in ExecutionException
        try {
            boom.get();
        } catch (Exception ignore) {
            // Expected: ignore here, since we’re demonstrating failure handling
        }


        pool.shutdown();


        System.out.println("REPORT_NOTE: CustomThreadFactory threads named, exception captured");
    }
}
