package Java_Threads.Easy_Level_Questions.volatileExamples.Q5;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * GOAL
 * ----
 * Provide *lock-free* reads for a frequently-read, rarely-updated mapping.

 * PATTERN
 * -------
 * 1) Readers use the current `volatile` Map reference directly (fast, no locks).
 * 2) Updaters **copy the map**, apply changes to the copy, then perform a single
 *    `volatile` write to *swap* the reference (atomic publish).
 *
 * WHY IT'S SAFE
 * -------------
 * - Readers see either the old immutable snapshot or the new snapshot.
 * - No reader ever sees the map *while it’s being modified* (updates happen on the copy).
 * - The `volatile` write establishes a happens-before edge to all future reads.
 */
public class CopyOnWriteDirectoryApp {

    // (A) Start with an immutable snapshot
    private static volatile Map<String, String> directory = Map.of(
            "101", "Alice",
            "102", "Bob",
            "103", "Carlos"
    );

    public static void main(String[] args) throws InterruptedException {
        Thread reader = new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                // (B) VOLATILE READ: grab current snapshot reference
                Map<String, String> snap = directory;

                // (C) Read from snapshot (no sync needed; treat as read-only)
                System.out.println("[reader] 102 -> " + snap.get("102"));
                sleep(200);
            }
        }, "reader");

        Thread refresher = new Thread(() -> {
            // (D) Build a new copy in isolation
            sleep(500);
            ConcurrentHashMap<String, String> next = new ConcurrentHashMap<>(directory);
            next.put("104", "Diana");
            next.put("102", "Bobby"); // update existing

            // (E) Freeze it and publish with a single VOLATILE WRITE (atomic swap)
            directory = Map.copyOf(next);
            System.out.println("[refresher] swapped directory");
        }, "refresher");

        reader.start(); refresher.start();
        reader.join(); refresher.join();
    }

    static void sleep(long ms){ try{ TimeUnit.MILLISECONDS.sleep(ms);} catch(InterruptedException ignored){} }
}
