package Java_Threads.Thread_Transitions.Full_LifeCycle;

import static Java_Threads.Thread_Transitions.Full_LifeCycle.LifecycleShowcaseApp.MONITOR;

public class Waiter implements Runnable
{
    @Override
    public void run()
    {
        synchronized (MONITOR) {
            try {
                MONITOR.wait(); // WAITING until main calls notifyAll()
            } catch (InterruptedException ignored) {}
        }
        // After notify, fall through and terminate
    }


 }

