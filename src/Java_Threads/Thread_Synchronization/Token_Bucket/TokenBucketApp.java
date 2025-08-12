package Java_Threads.Thread_Synchronization.Token_Bucket;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/*
 * Scenario:
 * - Many worker threads need permission to make outbound calls.
 * - A token bucket refills at a fixed rate (refill thread).
 * - Workers acquire() blocks on a Condition when no tokens are available.
 *
 * Why Lock/Condition:
 * - Finer control than intrinsic locks: await with timeout, multiple conditions, etc.
 */
public class TokenBucketApp
{

    public static void main(String[] args) throws InterruptedException {
        TokenBucket limiter = new TokenBucket(5 /*capacity*/, 2 /*refill per tick*/);

        // Refill thread
        Thread refiller = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    limiter.refill();
                    TimeUnit.MILLISECONDS.sleep(250); // 8 tokens/sec max
                }
            } catch (InterruptedException ignored) {}
        }, "refiller");
        refiller.setDaemon(true);
        refiller.start();

        // 6 clients trying to acquire permits
        for (int i = 0; i < 6; i++) {
            int id = i;
            new Thread(() -> clientWork(limiter, id), "client-" + id).start();
        }

        TimeUnit.SECONDS.sleep(3);
        System.out.println("Done.");
    }

    static void clientWork(TokenBucket limiter, int id) {
        for (int r = 0; r < 4; r++) {
            try {
                if (limiter.acquire(500, TimeUnit.MILLISECONDS)) {
                    System.out.printf("[%s] call allowed at %s%n",
                            Thread.currentThread().getName(), Instant.now());
                    // simulate API call
                    TimeUnit.MILLISECONDS.sleep(120);
                } else {
                    System.out.printf("[%s] rate-limited (timeout) at %s%n",
                            Thread.currentThread().getName(), Instant.now());
                }
            } catch (InterruptedException ignored) {
                return;
            }
        }
    }

}
