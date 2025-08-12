package Java_Threads.Thread_Creation.Order_Import_Pipeline;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * FLOW OVERVIEW
 * -------------
 * 1) main() creates:
 *    - a bounded BlockingQueue<String> (shared buffer)
 *    - an AtomicBoolean 'running' flag to coordinate shutdown
 * 2) Start one OrderReaderThread (extends Thread). It simulates reading lines (orders)
 *    from somewhere (file/API) and puts each line into the queue. It sleeps a bit
 *    to mimic I/O latency. If queue is full, put() blocks (natural backpressure).
 * 3) Start 3 OrderProcessor Runnable workers in a fixed thread pool. Each worker loops:
 *       poll() a line from the queue -> parse -> "process" (sleep) -> print result
 *    poll(timeout) keeps the loop responsive to shutdown (doesn't block forever).
 * 4) After ~2 seconds, main() flips 'running=false', interrupts the reader, and
 *    shuts down the pool. Consumers finish remaining queue items and exit.
 * 5) Print “Import finished.” and end.
 */
public class OrderImportApp
{
    public static void main(String[] args) throws InterruptedException {
        // Bounded queue: prevents producers from overwhelming consumers
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(64);

        // Shared flag to signal all threads to stop gracefully
        AtomicBoolean running = new AtomicBoolean(true);

        // (1) Start the single-purpose producer thread (extends Thread)
        //     We pass a small in-memory "source" list to simulate a data file/API.
        OrderReaderThread reader = new OrderReaderThread(
                queue,
                running,
                List.of("A1001,12.50", "A1002,99.00", "A1003,5.10", "A1004,220.00", "A1005,13.37")
        );
        reader.start();

        // (2) Start a small pool of Runnable consumers
        //     Fixed pool size: 3 workers reading from the same queue.
        ExecutorService workers = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            workers.submit(new OrderProcessor(queue, running));
        }

        // Allow the pipeline to run briefly
        TimeUnit.SECONDS.sleep(2);

        // (3) Begin graceful shutdown:
        //     - flip flag so loops can exit
        //     - interrupt the producer (to break out of sleep/put/take)
        //     - stop worker pool; we use shutdownNow to ensure exit in this demo
        running.set(false);
        reader.interrupt();
        workers.shutdownNow();

        // Optional: wait a little for workers to finish
        workers.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println("Import finished.");
    }

}
