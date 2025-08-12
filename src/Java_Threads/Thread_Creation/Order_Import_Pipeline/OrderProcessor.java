package Java_Threads.Thread_Creation.Order_Import_Pipeline;

import java.time.Instant;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

// -------- Consumer: Runnable (scales easily in a pool) --------
public class OrderProcessor implements Runnable
{

    private final BlockingQueue<String> in;
    private final AtomicBoolean running;

    OrderProcessor(BlockingQueue<String> in, AtomicBoolean running) {
        this.in = in;
        this.running = running;
    }

    @Override
    public void run()
    {
        // Name the worker so logs show which pool thread is doing the work
        Thread.currentThread().setName("order-worker-" + Thread.currentThread().getId());

        // Loop until:
        //  - running is false AND queue is empty (i.e., we drained the backlog),
        //  - or we get interrupted by shutdownNow().
        while (running.get() || !in.isEmpty()) {
            try {
                // poll with timeout keeps the loop responsive (won’t hang forever)
                String line = in.poll(200, TimeUnit.MILLISECONDS);
                if (line == null) continue;

                // Minimal "parsing": "orderId,amount"
                String[] parts = line.split(",");
                String orderId = parts[0];
                double amount = Double.parseDouble(parts[1]);

                // Simulate validation + DB persist
                TimeUnit.MILLISECONDS.sleep(80);

                System.out.printf("%s processed %s amount=%.2f at %s%n",
                        Thread.currentThread().getName(), orderId, amount, Instant.now());
            } catch (InterruptedException e) {
                // Pool shutdownNow() interrupts — exit cleanly
                break;
            }
        }
    }
}



