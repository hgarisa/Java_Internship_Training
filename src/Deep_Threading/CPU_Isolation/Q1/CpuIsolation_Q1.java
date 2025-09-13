package Deep_Threading.CPU_Isolation.Q1;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * CPU ISOLATION DEMO (high-level idea)
 * ------------------------------------
 * We simulate a machine that's busy doing heavy background work ("noisy analytics")
 * and, at the same time, we have a small latency-critical task that must respond quickly.
 *
 * Design:
 *  - A "noisy" ExecutorService runs infinite CPU loops to occupy cores (simulate load).
 *  - A dedicated single-thread ExecutorService ("critical") is our "isolated lane".
 *  - For each sample we measure: time to submit a trivial task to the critical lane
 *    and have it run and complete. This end-to-end time is the latency we record.
 *  - After collecting many samples, we sort them and report p50 and p99 percentiles.
 *
 * Why it shows isolation:
 *  - Even with noise, the dedicated single-thread executor often keeps latency lower
 *    and more stable than if we shared the same busy pool. The numbers you print
 *    give you a quick, quantitative feel for that difference.
 */
public class CpuIsolation_Q1
{
    public static void main(String[] args) throws Exception {

        // Number of background workers = number of available CPUs (at least 4).
        // Using 'n' threads here maximizes contention to make the demo obvious.
        int n = Math.max(4, Runtime.getRuntime().availableProcessors());

        // ---- NOISY BACKGROUND POOL ------------------------------------------
        // This pool represents "analytics" or "batch" jobs that burn CPU nonstop.
        // Each task loops while not interrupted, doing meaningless work to keep
        // the core hot. This creates scheduling pressure on the OS.
        ExecutorService noisy = Executors.newFixedThreadPool(n);
        for (int i = 0; i < n; i++) {
            noisy.submit(() -> {
                long x = 0;
                // Busy loop. We don't sleep on purpose; we want constant pressure.
                while (!Thread.currentThread().isInterrupted()) {
                    // nanoTime() is just a cheap operation to keep the JIT from
                    // optimizing the loop away, and it "does work" each iteration.
                    x += System.nanoTime();
                }
                // When shutdownNow() interrupts us, we exit the loop and finish.
            });
        }

        // ---- DEDICATED "CRITICAL" LANE --------------------------------------
        // This single-thread executor is our "isolated" path for latency-critical work.
        // We also nudge its priority up slightly so the OS prefers running it when ready.
        ExecutorService critical = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r, "critical");
            t.setPriority(Thread.NORM_PRIORITY + 1); // small bump; not required
            return t;
        });


        int samples = 600;
        long[] latency = new long[samples];

        // ---- MEASUREMENT LOOP -----------------------------------------------
        for (int i = 0; i < samples; i++) {


            long start = System.nanoTime();

            // We'll use a CompletableFuture as a "latch". The Runnable we submit
            // will complete it immediately when it runs. Measuring from now until
            // cf.get() returns gives us the scheduling + execution latency.
            CompletableFuture<Void> cf = new CompletableFuture<>();

            // Submit a *trivial* task to the critical lane.
            // If the lane is truly isolated (and the OS cooperates), this should
            // run quickly even while other cores are busy with noise.
            critical.execute(() -> cf.complete(null));

            // Block until the critical task has actually run.
            // The time spent waiting here is exactly our latency of interest.
            cf.get();

            // Record end-to-end latency for this sample.
            latency[i] = System.nanoTime() - start;


        }

        // Cleanly stop the background pressure and the critical executor.
        noisy.shutdownNow();     // sends interrupts to all "noisy" workers
        critical.shutdownNow();  // stop the dedicated lane

        // ---- REPORTING ------------------------------------------------------
        // Percentiles are more informative than averages for latency.
        // Sort the samples so we can pick p50 (median) and p99 (tail latency).
        Arrays.sort(latency);
        long p50 = latency[samples / 2];               // 50th percentile (median)
        long p99 = latency[(int)(samples * 0.99)];     // 99th percentile (worst tail)

        System.out.printf("REPORT_NOTE: CpuIsolation p50=%dns p99=%dns%n", p50, p99);

    }
}
