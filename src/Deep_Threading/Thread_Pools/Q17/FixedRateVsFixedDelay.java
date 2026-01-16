package Deep_Threading.Thread_Pools.Q17;

import java.util.concurrent.*;
import java.time.*;
public class FixedRateVsFixedDelay
{
    /*

  What this code does

It creates a scheduled thread pool with 1 worker thread.

It runs two scheduled tasks:

Fixed-rate task → runs every 1 second, based on the clock, regardless of how long the previous run took.

Fixed-delay task → waits 1 second after the previous run finishes before starting the next one.

Runs for 6 seconds, then shuts down.

     */
    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService sch = Executors.newScheduledThreadPool(1);

        // fixed-rate: tries to keep the *rate* (may bunch if work is slow)
        sch.scheduleAtFixedRate(() -> log("rate") , 0 , 1 , TimeUnit.SECONDS);
        //Starts immediately (0 initial delay).
        //Then repeats every 1 second, relative to the scheduled time.
        //If a task takes longer than the interval, the scheduler tries to “catch up” — tasks may bunch together.
        // Think of a clock ticking every 1s:
        //Task must try to keep up with the tick marks, even if it lags.


        // fixed-delay: waits *after* each run completes (no catch-up)
        sch.scheduleWithFixedDelay(() ->  {

            log("Delay-Start");
            try { Thread.sleep(1300); } catch (InterruptedException ignored) {}
            log("Delay-End");
        } , 0 , 1 , TimeUnit.SECONDS);


      Thread.sleep(6000);
      sch.shutdown();

    }

    static void log(String label)
    {
        System.out.printf("%s at %s on %s%n" , label , LocalTime.now() , Thread.currentThread().getName());

    }

    // This is a static method called log
    // It takes one argument : A String(label)
    // It doesn't return anything (void)
    // Its purpose is to print a formatted log line

    // System.out.printf(...) . printf =  print formatted
    // It works like String.format , but it directly points to console.
    // %s = placeholder for a string. %n = new line (like \n, but platform-independent).
    // So this format expects three values to fill the %s %s %s.

}
