package Java_Threads.Thread_Transitions.Priority_scheduler;

import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.LongAdder;

public class Hasher implements Runnable
{
    private final String tag;
    private final LongAdder counter;
    private final AtomicBoolean running;
    Hasher(String tag, LongAdder counter, AtomicBoolean running) {
        this.tag = tag; this.counter = counter; this.running = running;
    }
    @Override public void run() {
        long x = 0x9E3779B97F4A7C15L; // golden ratio constant
        while (running.get()) {
            // Busy loop (RUNNABLE)
            x ^= (x << 7); x ^= (x >>> 9); x ^= (x << 8);
            if ((counter.longValue() & 0xFFF) == 0 && "lo".equals(tag)) {
                Thread.yield(); // hint scheduler; lo-priority yields sometimes
            }
            counter.increment();
        }
    }


}
