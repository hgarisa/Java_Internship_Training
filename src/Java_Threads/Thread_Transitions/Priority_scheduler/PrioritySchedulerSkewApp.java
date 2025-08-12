package Java_Threads.Thread_Transitions.Priority_scheduler;

import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.LongAdder;

/*
 * Scenario:
 *  - Two CPU-bound "Hasher" threads with different priorities
 *  - One I/O-ish sleeper thread (simulates network wait)
 * See how counts differ (OS/JVM dependent). We also call yield() in low priority to amplify skew.
 */

public class PrioritySchedulerSkewApp
{
    public static void main(String[] args) throws InterruptedException {
        AtomicBoolean running = new AtomicBoolean(true);
        LongAdder hiCount = new LongAdder();
        LongAdder loCount = new LongAdder();

        Thread hi = new Thread(new Hasher("hi", hiCount, running), "hi-priority");
        Thread lo = new Thread(new Hasher("lo", loCount, running), "lo-priority");
        Thread io = new Thread(new IoSleeper(running), "io-ish");

        hi.setPriority(Thread.NORM_PRIORITY + 1);
        lo.setPriority(Thread.NORM_PRIORITY - 1);
        io.setPriority(Thread.NORM_PRIORITY);

        hi.start(); lo.start(); io.start();

        for (int i = 0; i < 8; i++) {
            System.out.printf("%s  hi=%s lo=%s io=%s  counts: hi=%d lo=%d%n",
                    Instant.now(), hi.getState(), lo.getState(), io.getState(),
                    hiCount.sum(), loCount.sum());
            TimeUnit.MILLISECONDS.sleep(250);
        }
        running.set(false);
        hi.join(); lo.join(); io.join();

        System.out.println("Final counts: hi=" + hiCount.sum() + " lo=" + loCount.sum());
    }
}
