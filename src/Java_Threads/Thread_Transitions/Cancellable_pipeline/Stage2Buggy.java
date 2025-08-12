package Java_Threads.Thread_Transitions.Cancellable_pipeline;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
public class Stage2Buggy implements Runnable
{
    private final AtomicBoolean running;
    public Stage2Buggy(AtomicBoolean running) {
        this.running = running;
    }
    @Override
    public void run()
    {
        try {
            while (running.get()) {
                // "Bug": sometimes we do an overly long sleep (hang), broken by interrupt
                TimeUnit.MILLISECONDS.sleep(5_000);
            }
        } catch (InterruptedException e) {
            System.out.println("[stage2] interrupted; exiting buggy wait");
        }
    }


}
