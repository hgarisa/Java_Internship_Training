package Deep_Threading.Thread_Pools.Q8;
import java.util.concurrent.*;

/**
 * DYNAMIC RESIZING THREAD POOL DEMO
 * ---------------------------------
 * Goal:
 *  - Show how a ThreadPoolExecutor can dynamically expand and contract.
 *  - Start with a small core pool (1 thread).
 *  - Submit a burst of tasks → pool grows up to max (8).
 *  - After tasks finish and threads are idle, threads are trimmed
 *    (even core threads) because we enabled core thread timeout.
 *
 * Why it matters:
 *  - Saves resources in real systems where workload is bursty:
 *    scale up when busy, shrink back down when idle.
 */
public class ExTP08_DynamicResizing
{
    public static void main(String[] args) throws Exception {

        // Create a ThreadPoolExecutor manually:
        // corePoolSize = 1 (starts with 1 worker)
        // maximumPoolSize = 8 (can grow up to 8 threads during load)
        // keepAliveTime = 5s (idle threads live max 5s before removal)
        // workQueue = LinkedBlockingQueue (unbounded queue for tasks)
        ThreadPoolExecutor ex = new ThreadPoolExecutor(
                1,                         // core threads
                8,                         // max threads
                5, TimeUnit.SECONDS,       // keepAlive time
                new LinkedBlockingQueue<>() // queue to hold tasks
        );

        // Important: by default, core threads live forever.
        // allowCoreThreadTimeOut(true) lets even *core* threads die after keepAliveTime.
        ex.allowCoreThreadTimeOut(true);

        // ---- BURST OF TASKS ----
        // Submit 100 tasks that each sleep for 100ms.
        // Since tasks block for some time, more threads will be created
        // (up to 8) to handle them in parallel.
        for (int i = 0; i < 100; i++) {
            ex.execute(() -> {
                try {
                    Thread.sleep(100); // simulate work
                } catch (InterruptedException ignored) {}
            });
        }

        // ---- OBSERVE DURING BURST ----
        // At this point, many tasks are still running, so:
        // - pool.getPoolSize() shows how many worker threads exist (should be >1).
        // - activeCount shows how many threads are currently working.
        // - queue.size() shows how many tasks are still waiting in the queue.
        System.out.printf("REPORT_NOTE: DuringBurst pool=%d active=%d queue=%d%n",
                ex.getPoolSize(), ex.getActiveCount(), ex.getQueue().size());

        // ---- WAIT FOR COMPLETION ----
        // Shutdown gracefully (no new tasks accepted, finish all submitted).
        ex.shutdown();
        // Wait up to 30 seconds for everything to finish.
        ex.awaitTermination(30, TimeUnit.SECONDS);

        // ---- AFTER BURST ----
        // At this point, the pool is idle, and because of allowCoreThreadTimeOut(true),
        // all worker threads (including core threads) will time out and exit.
        // So effectively, the pool shrinks back down to 0 threads.
        System.out.println("REPORT_NOTE: AfterShutdown (threads trimmed by timeout if idle)");
    }

}
