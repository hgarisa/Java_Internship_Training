package Java_Threads.Thread_Synchronization.Batch_Event;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

public class BatchBuffer
{
    private final int capacity;
    private final int batchSize;
    private final Queue<String> q = new ArrayDeque<>();
    private final Lock lock = new ReentrantLock(true); // fair to reduce producer starvation
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    BatchBuffer(int capacity, int batchSize) {
        this.capacity = capacity;
        this.batchSize = batchSize;
    }

    public void add(String event) {
        lock.lock();
        try {
            while (q.size() == capacity) {
                notFull.await();
            }
            q.add(event);
            if (q.size() >= batchSize) {
                notEmpty.signal(); // wake flusher immediately when enough events
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void flushLoop(long maxWaitMs) {
        while (true) {
            lock.lock();
            try {
                long deadline = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(maxWaitMs);

                // Wait until batchSize or until time threshold expires
                while (q.size() < batchSize) {
                    long remaining = deadline - System.nanoTime();
                    if (remaining <= 0 && !q.isEmpty()) break; // time-based flush
                    if (remaining <= 0) notEmpty.await();      // nothing to flush → wait indefinitely
                    else notEmpty.awaitNanos(remaining);
                }

                if (q.isEmpty()) continue; // nothing to do

                int n = Math.min(q.size(), batchSize);
                System.out.printf("[flusher %s] flushing n=%d (q=%d) at %s%n",
                        Thread.currentThread().getName(), n, q.size(), Instant.now());
                for (int i = 0; i < n; i++) q.poll();

                notFull.signalAll(); // let producers push more
            } catch (InterruptedException e) {
                return;
            } finally {
                lock.unlock();
            }
        }
    }



}
