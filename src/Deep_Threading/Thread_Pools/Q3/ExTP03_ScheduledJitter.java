package Deep_Threading.Thread_Pools.Q3;

import java.util.*;
import java.util.concurrent.*;

/**
 * SCHEDULED JITTER PROBE (1 ms heartbeat)
 * ---------------------------------------
 * Goal:
 *  - Fire a task every 1 millisecond and record "jitter" = (actual_time - expected_time).
 *  - After S samples, compute percentiles (p50/p99) to summarize scheduler quality.
 *
 * Why jitter happens:
 *  - The OS scheduler, JVM GC/JIT, other processes, and timer resolution can delay runs.
 *  - Even with scheduleAtFixedRate (fixed period), real executions may drift/lag.
 *
 * What we do:
 *  1) Capture a 'base' timestamp in nanoseconds.
 *  2) For tick i, the *ideal* expected time is base + i * 1_000_000 ns (1 ms).
 *  3) When the Runnable actually runs, we record: now - expected  ==> jitter (ns).
 *  4) Sort all jitters and print p50 and p99.
 */
public class ExTP03_ScheduledJitter
{
    public static void main(String[] args) throws Exception {
        // A single-threaded scheduled executor to fire our heartbeat.
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

        // Total samples to collect.
        final int S = 3000;

        // Store jitters (ns). Pre-size the list to avoid resizes during the test.
        List<Long> js = new ArrayList<>(S);

        // 'base' = reference start time (monotonic, high-resolution).
        final long base = System.nanoTime();

        // The heartbeat task:
        // - 'i' counts how many times we've run.
        // - For each run, compute "expected time" from base + i * 1ms, then record (now - expected).
        Runnable hb = new Runnable() {
            int i = 0;

            @Override public void run() {
                // Ideal timestamp for the i-th tick (1_000_000 ns = 1 ms).
                long expected = base + (long) i * 1_000_000L;

                // Actual time now.
                long now = System.nanoTime();

                // Jitter = how late/early we are relative to the ideal schedule.
                //  - Positive value => we are late by that many nanoseconds.
                //  - Negative value => we fired a bit early (uncommon but possible).
                js.add(now - expected);

                // Advance sample counter; once we have S samples, stop the scheduler.
                if (++i >= S) {
                    // shutdown() lets already-scheduled tasks finish (there are none pending after S).
                    ses.shutdown();
                }
            }
        };

        // Start firing immediately (initialDelay=0) at a fixed period of 1 ms.
        // scheduleAtFixedRate tries to keep a constant period, compensating for delays by
        // scheduling the next run based on the *original* start time, not the finish time.
        ses.scheduleAtFixedRate(hb, 0, 1, TimeUnit.MILLISECONDS);

        // Wait up to 30s for all samples to be collected and executor to shut down.
        ses.awaitTermination(30, TimeUnit.SECONDS);

        // Sort jitters to easily pick percentiles.
        Collections.sort(js);

        // Median (50th percentile) and 99th percentile (tail latency).
        long p50 = js.get(S / 2);
        long p99 = js.get((int) (S * 0.99));

        // Compact line to paste into your report.
        System.out.printf("REPORT_NOTE: ScheduledJitter p50=%dns p99=%dns%n", p50, p99);
    }
}
