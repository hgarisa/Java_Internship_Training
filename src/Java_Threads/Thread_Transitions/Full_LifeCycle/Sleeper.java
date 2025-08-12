package Java_Threads.Thread_Transitions.Full_LifeCycle;

import java.util.concurrent.TimeUnit;

public class Sleeper implements Runnable
{
    @Override
    public void run()
    {
        try {
            TimeUnit.MILLISECONDS.sleep(700); // TIMED_WAITING
        } catch (InterruptedException ignored) {}
    }

}

