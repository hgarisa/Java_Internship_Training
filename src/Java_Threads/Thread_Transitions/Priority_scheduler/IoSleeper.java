package Java_Threads.Thread_Transitions.Priority_scheduler;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.LongAdder;

public class IoSleeper implements  Runnable
{
    private final AtomicBoolean running;
        IoSleeper(AtomicBoolean running) { this.running = running; }
        @Override public void run() {
        try {
            while (running.get()) {
                // Simulate blocking I/O with sleep (TIMED_WAITING)
                TimeUnit.MILLISECONDS.sleep(80);
            }
        }
         catch (InterruptedException ignored) {}
      }

}


