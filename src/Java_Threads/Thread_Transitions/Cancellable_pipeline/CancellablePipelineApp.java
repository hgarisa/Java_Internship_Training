package Java_Threads.Thread_Transitions.Cancellable_pipeline;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/*
 * Scenario:
 *  - stage1 (well-behaved) -> stage2 (buggy; may hang) -> stage3 (waiting on stage2)
 *  - main() uses join(timeout) to avoid indefinite wait, then interrupts stage2 to recover.
 * States seen: stage2 often RUNNABLE (spins) or TIMED_WAITING (sleep), stage3 WAITING (queue),
 * main goes TIMED_WAITING during join(timeout).
 */
public class CancellablePipelineApp
{
    public static void main(String[] args) throws InterruptedException {
        AtomicBoolean running = new AtomicBoolean(true);

        Thread stage1 = new Thread(new Stage1(running), "stage1");
        Thread stage2 = new Thread(new Stage2Buggy(running), "stage2-buggy");
        Thread stage3 = new Thread(new Stage3(running), "stage3");

        stage1.start();
        stage2.start();
        stage3.start();

        // Observe for a bit
        for (int i = 0; i < 6; i++) {
            System.out.printf("%s  s1=%s s2=%s s3=%s%n",
                    Instant.now(), stage1.getState(), stage2.getState(), stage3.getState());
            TimeUnit.MILLISECONDS.sleep(200);
        }

        // Try graceful stop with join(timeout)
        running.set(false);
        if (!joinWithTimeout(stage2, 800)) {
            System.out.println("stage2 still not done; interrupting...");
            stage2.interrupt(); // break sleeps/spins
        }

        stage1.join(); stage3.join(); stage2.join();
        System.out.println("Pipeline terminated.");
    }

    static boolean joinWithTimeout(Thread t, long ms) throws InterruptedException {
        t.join(ms); // main -> TIMED_WAITING
        return (t.getState() == Thread.State.TERMINATED);
    }


}
