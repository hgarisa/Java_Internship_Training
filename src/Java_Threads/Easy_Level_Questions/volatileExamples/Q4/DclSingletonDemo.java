package Java_Threads.Easy_Level_Questions.volatileExamples.Q4;

/**
 * GOAL
 * ----
 * Lazily create a single global instance, minimizing synchronization on the *fast path*.

 * WHY `volatile` IS REQUIRED
 * --------------------------
 * - Without `volatile`, the JVM/CPU could reorder writes so another thread might
 *   see `INSTANCE` as non-null *before* the constructor has finished (partially
 *   constructed object). That's a real data-race.
 * - With `volatile`, the write `INSTANCE = new MyService()` *happens-before* any
 *   later read of `INSTANCE`, and construction can't be reordered past the publish.
 *
 * FLOW
 * ----
 * - First call: both reads of `INSTANCE` see null → enter synchronized block →
 *   construct → volatile write publishes fully-initialized object.
 * - Later calls: first read sees non-null → returns immediately (no locking).
 */
public class DclSingletonDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> System.out.println(Thread.currentThread().getName()
                + " got " + MyService.instance());

        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");
        t1.start(); t2.start();
        t1.join(); t2.join();
    }
}

final class MyService {
    // (1) VOLATILE: guarantees visibility + prevents reordering around publish
    private static volatile MyService INSTANCE;

    // Some real state to emphasize “fully initialized” matters
    private final byte[] bigCache = new byte[1_000_000];

    private MyService() { /* heavy init could go here */ }

    static MyService instance() {
        // (2) First, an UNSYNCHRONIZED read ("fast path")
        MyService result = INSTANCE;
        if (result == null) {
            // (3) Only synchronize when instance is not yet initialized
            synchronized (MyService.class) {
                // (4) Re-check inside the lock (another thread may have initialized)
                result = INSTANCE;
                if (result == null) {
                    // (5) Construct fully, then
                    result = new MyService();

                    // (6) VOLATILE WRITE: safely publish; construction can't be observed half-done
                    INSTANCE = result;
                }
            }
        }
        // (7) Return initialized reference (either from fast path or after constructing)
        return result;
    }

    @Override public String toString() { return "MyService@" + Integer.toHexString(System.identityHashCode(this)); }
}
