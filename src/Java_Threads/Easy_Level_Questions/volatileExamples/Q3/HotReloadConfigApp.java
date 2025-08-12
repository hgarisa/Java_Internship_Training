package Java_Threads.Easy_Level_Questions.volatileExamples.Q3;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * GOAL
 * ----
 * Show how to publish a *new* configuration to many reader threads instantly,
 * without locks, by swapping a single `volatile` reference that points to an
 * *immutable* snapshot object.

 * WHY IT WORKS
 * ------------
 * - A write to a `volatile` field *happens-before* every subsequent read of that field.
 * - We replace (not mutate) the config. Readers either see the old snapshot or the
 *   new snapshot, never a partially-mutated mix.
 *
 * PROGRAM FLOW
 * ------------
 * 1) `CURRENT` holds the initial config snapshot.
 * 2) The READER thread repeatedly performs a *volatile read* (`Config c = CURRENT`)
 *    and uses it. No locking; the read is fresh because the field is volatile.
 * 3) The RELOADER thread constructs a completely new `Config` instance *off to the side*,
 *    then publishes it by writing the reference to `CURRENT` (a *volatile write*).
 * 4) After the write, all future reader reads will observe the new snapshot.
 */
public class HotReloadConfigApp {

    // Immutable snapshot; safe to share freely.
    record Config(Duration timeout, Set<String> blockedCountries) {}

    // VOLATILE reference: the only mutable thing is which object we point to.
    private static volatile Config CURRENT =
            new Config(Duration.ofMillis(300), Set.of("KP", "SY"));

    public static void main(String[] args) throws InterruptedException {
        Thread reader = new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                // (A) VOLATILE READ: returns the latest published snapshot (or the initial one)
                Config c = CURRENT;

                // (B) Use fields of the immutable snapshot (no further sync needed)
                System.out.println("[reader] timeout=" + c.timeout()
                        + " blocked=" + c.blockedCountries());

                sleep(250);
            }
        }, "reader");

        Thread reloader = new Thread(() -> {
            // (C) After some time, build a NEW snapshot
            sleep(600);
            Config next = new Config(Duration.ofMillis(120), Set.of("KP", "SY", "IR"));

            // (D) VOLATILE WRITE: publish the new snapshot atomically
            CURRENT = next;

            // After this point, every subsequent volatile read sees `next`
            System.out.println("[reloader] published new config");
        }, "reloader");

        reader.start();
        reloader.start();
        reader.join();
        reloader.join();
    }

    static void sleep(long ms) { try { TimeUnit.MILLISECONDS.sleep(ms); } catch (InterruptedException ignored) {} }
}
