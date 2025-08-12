package Java_Threads.Thread_Synchronization.Batch_Event;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/*
 * Scenario:
 * - Multiple producers append events to a bounded buffer.
 * - One flusher thread consumes in batches, either when buffer is "full enough"
 *   OR a time threshold passes (whichever first).
 *
 * Why two Conditions:
 * - Producers await notFull when capacity reached.
 * - Flusher awaits notEmpty with a timed wait to implement "flush by time".
 */

public class BatchEventSinkApp
{
    public static void main(String[] args) throws InterruptedException {
        BatchBuffer buffer = new BatchBuffer(20, 8); // capacity 20, batch 8
        Thread flusher = new Thread(() -> buffer.flushLoop(300), "flusher");
        flusher.setDaemon(true);
        flusher.start();

        // 3 producers
        for (int i = 0; i < 3; i++) {
            int id = i;
            Thread t = new Thread(() -> produce(buffer, id), "producer-" + id);
            t.start();
        }

        TimeUnit.SECONDS.sleep(4);
        System.out.println("Shutting down demo.");
    }

    static void produce(BatchBuffer buffer, int id) {
        for (int i = 0; i < 30; i++) {
            String evt = "e" + id + "-" + i;
            buffer.add(evt);
            sleep(ThreadLocalRandom.current().nextInt(20, 60));
        }
    }

    static void sleep(long ms) { try { Thread.sleep(ms); } catch (InterruptedException ignored) {} }


}
