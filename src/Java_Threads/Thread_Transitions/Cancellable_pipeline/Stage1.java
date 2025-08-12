package Java_Threads.Thread_Transitions.Cancellable_pipeline;

import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
public class Stage1 implements Runnable {

    private final AtomicBoolean running;
    Stage1(AtomicBoolean running) { this.running = running; }
    @Override
    public void run()
    {
        try {
            while (running.get()) {
                // Pretend to produce items
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException ignored) {}
    }


}
