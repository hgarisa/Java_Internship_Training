package Java_Threads.Thread_Transitions.Blocking_queue;

import java.time.Instant;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import static Java_Threads.Thread_Transitions.Blocking_queue.BlockingQueueTransitionsApp.AUDIT_LOCK;

public class Consumer implements Runnable
{
    private final BlockingQueue<String> q;
    private final AtomicBoolean running;

    Consumer(BlockingQueue<String> q, AtomicBoolean running) { this.q = q; this.running = running; }
    @Override
    public void run()
    {
        try {
            while (running.get() || !q.isEmpty()) {
                String m = q.poll(150, TimeUnit.MILLISECONDS); // WAITING when empty
                if (m == null) continue;
                // Try to enter same monitor; may become BLOCKED if producer holds it
                synchronized (AUDIT_LOCK) {
                    Thread.sleep(30);
                }
            }
        } catch (InterruptedException ignored) {}

    }
}
