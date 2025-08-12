package Java_Threads.Easy_Level_Questions.volatileExamples.Q6;

import java.util.concurrent.TimeUnit;

/**
 * GOAL
 * ----
 * Stop a hot loop in another thread **promptly** and **safely** using a volatile flag.

 * WHY VOLATILE
 * ------------
 * - The worker cached value must refresh; a normal non-volatile boolean could be
 *   hoisted or never reloaded under JIT optimizations.
 * - `running=false` (volatile write) in main *happens-before* any subsequent
 *   volatile read in the worker, so the worker sees the change quickly.

 * FLOW
 * ----
 * 1) Worker starts a tight loop that checks `running` each iteration.
 * 2) Main sleeps a bit, then sets `running=false` (volatile write).
 * 3) Worker’s next volatile read sees `false`, exits the loop, prints result, terminates.
 * 4) Main joins the worker.
 */
public class StopFlagSpinDemo {

    // (A) VOLATILE stop signal shared between threads
    private static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            long sum = 0;
            // (B) Hot loop: visibility of `running` matters!
            while (running) {
                sum += 1;

                // (C) Optional CPU-friendly hint while spinning (Java 9+)
                if ((sum & 0xFFFF) == 0) {
                    Thread.onSpinWait();
                }
            }
            // (D) We observed running=false and exited; safe to report
            System.out.println("[worker] stopped with sum=" + sum);
        }, "worker");

        worker.start();

        // (E) Let the worker run for a bit
        TimeUnit.MILLISECONDS.sleep(500);

        // (F) VOLATILE WRITE: publish stop request; worker will see it promptly
        running = false;

        // (G) Join so main waits for worker to reach TERMINATED
        worker.join(2000);
        System.out.println("[main] joined=" + !worker.isAlive());
    }
}
