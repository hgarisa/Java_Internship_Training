package Deep_Threading.Thread_Pools.Q5;
import java.util.concurrent.*;
import java.util.*;

/**
 * COMPLETION SERVICE DEMO
 * -----------------------
 * Goal:
 *  - Run a batch of tasks that take different (random) times to finish.
 *  - Compare how we collect results:
 *      * With plain ExecutorService: we’d store Futures and check them in submission order.
 *        => risk: waiting for a slow task blocks us from processing earlier-finished tasks.
 *      * With CompletionService: results become available in *completion order*.
 *        => we can consume fast results right away without waiting for slow ones.
 * Flow of this program:
 *  1) Create a pool of 4 worker threads.
 *  2) Wrap it with an ExecutorCompletionService<String>.
 *  3) Submit 10 tasks, each with random delay 100–500 ms.
 *  4) Collect results using cs.take().get(), which gives us tasks as they *finish*,
 *     not in submission order.
 *  5) Print each finished result immediately.
 *  6) Measure total wall-clock time and print as a REPORT_NOTE.
 */
public class ExTP05_CompletionService
{
    public static void main(String[] args) throws Exception {
        // Fixed pool with 4 threads: up to 4 tasks run in parallel.
        ExecutorService pool = Executors.newFixedThreadPool(4);

        // CompletionService wraps the pool and tracks finished tasks in a queue.
        CompletionService<String> cs = new ExecutorCompletionService<>(pool);

        // Random generator with seed for repeatable results (same delays every run).
        Random rnd = new Random(42);

        int N = 10; // number of tasks to submit

        // ---- SUBMIT TASKS ----------------------------------------------------
        for (int i = 0; i < N; i++) {
            final int id = i;                           // capture task id
            int delay = 100 + rnd.nextInt(400);         // delay between 100–499 ms

            // Each task just sleeps for `delay` ms, then returns a message.
            cs.submit(() -> {
                Thread.sleep(delay);
                return "task-" + id + " in " + delay + "ms";
            });
        }

        long start = System.currentTimeMillis();

        // ---- COLLECT RESULTS -------------------------------------------------
        for (int i = 0; i < N; i++) {
            // cs.take() blocks until *some* task is finished, then returns its Future.
            // This means we always process whichever task completes first.
            String r = cs.take().get(); // .get() retrieves the return value
            System.out.println("DONE: " + r);
        }

        // ---- CLEANUP ---------------------------------------------------------
        pool.shutdown();

        System.out.printf("REPORT_NOTE: CompletionService totalMs=%d%n",
                System.currentTimeMillis() - start);
    }

}
