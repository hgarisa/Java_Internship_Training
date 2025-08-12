package Java_Threads.Thread_Transitions.Blocking_queue;
import java.time.Instant;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static Java_Threads.Thread_Transitions.Blocking_queue.BlockingQueueTransitionsApp.AUDIT_LOCK;

public class Producer implements Runnable
{
    private final BlockingQueue<String> q;
    private final AtomicBoolean running;
    Producer(BlockingQueue<String> q, AtomicBoolean running) { this.q = q; this.running = running; }
    @Override
    public void run()
    {

        int i = 0;
        try {
            while (running.get()) {
                // Offer with timeout -> TIMED_WAITING if full
                boolean ok = q.offer("msg-" + (i++), 120, TimeUnit.MILLISECONDS);
                if (ok) {
                    // Enter AUDIT_LOCK sometimes -> consumers trying to audit become BLOCKED
                    synchronized (AUDIT_LOCK) {
                        Thread.sleep(40);
                    }
                } else {
                    // queue was full
                    Thread.yield();
                }
            }
        }
        catch (InterruptedException ignored) {}

    }
}
