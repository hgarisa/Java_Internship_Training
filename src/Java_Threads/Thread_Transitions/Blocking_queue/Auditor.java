package Java_Threads.Thread_Transitions.Blocking_queue;
import java.time.Instant;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static Java_Threads.Thread_Transitions.Blocking_queue.BlockingQueueTransitionsApp.AUDIT_LOCK;

public class Auditor  implements Runnable
{
    @Override
    public void run() {
        try {
            // Periodically hog the monitor to force BLOCKED in others
            for (int k = 0; k < 8; k++) {
                synchronized (AUDIT_LOCK) {
                    System.out.println("[auditor] holding AUDIT_LOCK...");
                    Thread.sleep(120);
                }
                Thread.sleep(120);
            }
        } catch (InterruptedException ignored) {}
    }
}
