package Java_Threads.Thread_Transitions.Blocking_queue;
import java.time.Instant;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
/*
 * Scenario:
 *  - Producer offers with timeout (TIMED_WAITING if queue is full).
 *  - Consumer takes (WAITING when empty).
 *  - Auditor holds a long synchronized block -> others attempting same lock become BLOCKED.
 */
public class BlockingQueueTransitionsApp
{
    static final Object AUDIT_LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        AtomicBoolean running = new AtomicBoolean(true);
        BlockingQueue<String> q = new LinkedBlockingQueue<>(5);

        Thread producer = new Thread(new Producer(q, running), "producer");
        Thread consumer = new Thread(new Consumer(q, running), "consumer");
        Thread auditor  = new Thread(new Auditor(), "auditor");
        Thread reporter = new Thread(() -> logStates(producer, consumer, auditor), "reporter");

        producer.start(); consumer.start(); auditor.start(); reporter.start();

        TimeUnit.SECONDS.sleep(3);
        running.set(false);
        producer.join(); consumer.join();
        // reporter stops after producer/consumer terminate
        System.out.println("Done.");
    }

    static void logStates(Thread... ts) {
        try {
            for (int i = 0; i < 20; i++) {
                System.out.printf("%s  ", Instant.now());
                for (Thread t : ts) System.out.printf("%s=%s  ", t.getName(), t.getState());
                System.out.println();
                Thread.sleep(150);
            }
        } catch (InterruptedException ignored) {}
    }

}
