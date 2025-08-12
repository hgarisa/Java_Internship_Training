package Java_Threads.Thread_Synchronization.Token_Bucket;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

// --------- Token bucket with explicit lock + condition ---------
public class TokenBucket
{
    private final int capacity;
    private int tokens;
    private final int perRefill;

    private final Lock lock = new ReentrantLock();
    private final Condition tokensAvailable = lock.newCondition();

    TokenBucket(int capacity, int perRefill) {
        this.capacity = capacity;
        this.tokens = capacity; // start full
        this.perRefill = perRefill;
    }

    boolean acquire(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        lock.lock();
        try {
            while (tokens == 0) {
                if (nanos <= 0L) return false; // timeout
                nanos = tokensAvailable.awaitNanos(nanos);
            }
            tokens--;
            return true;
        } finally {
            lock.unlock();
        }
    }

    void refill() {
        lock.lock();
        try {
            int before = tokens;
            tokens = Math.min(capacity, tokens + perRefill);
            if (tokens > before) tokensAvailable.signalAll();
        } finally {
            lock.unlock();
        }
     }

 }

