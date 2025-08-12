package Java_Threads.Thread_Transitions.Full_LifeCycle;

import static Java_Threads.Thread_Transitions.Full_LifeCycle.LifecycleShowcaseApp.MONITOR;

public class Blocker implements Runnable
{
    @Override
    public void run()
    {
        // Try to enter MONITOR while main holds it -> BLOCKED
        synchronized (MONITOR) {
            // Got it eventually
        }
    }
}
