package Java_Threads.Thread_Creation.Order_Import_Pipeline;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

// -------- Producer: extends Thread (a single responsibility: feed the queue) --------
public class OrderReaderThread extends Thread
{
    private final BlockingQueue<String> out;
    private final AtomicBoolean running;
    private final List<String> source;

    OrderReaderThread(BlockingQueue<String> out, AtomicBoolean running, List<String> source) {
        super("order-reader"); // give the thread a helpful name for logs
        setDaemon(true);   // daemon → JVM may exit even if still alive
        this.out = out; this.running = running; this.source = source;
    }

    @Override public void run() {
        try {
            // Iterate the "source" lines and place them on the queue
            for (String line : source) {
                if (!running.get()) break; // cooperative stop if main flipped the flag
                // Simulate I/O: e.g., disk or network delay
                TimeUnit.MILLISECONDS.sleep(120);

                // put() blocks if the queue is full → natural flow-control/backpressure
                out.put(line); // blocks if queue is full (backpressure)
            }
        } catch (InterruptedException ignored) {}
    }



}
