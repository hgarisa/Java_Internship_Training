package Java_Threads.Thread_Transitions.Cancellable_pipeline;

import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Stage3 implements Runnable
{
    private final AtomicBoolean running;
    Stage3(AtomicBoolean running) { this.running = running; }
    @Override
    public void run()
    {

        try {
            while (running.get()) {
                // "Wait" for stage2 output; we simulate with small sleeps
                TimeUnit.MILLISECONDS.sleep(120);
            }
        } catch (InterruptedException ignored) {}

    }
}
