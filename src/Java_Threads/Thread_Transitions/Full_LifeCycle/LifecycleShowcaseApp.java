package Java_Threads.Thread_Transitions.Full_LifeCycle;

import java.time.Instant;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/*
 * Scenario:
 *  - sleeper: sleep -> TIMED_WAITING
 *  - waiter: wait() on MONITOR -> WAITING; later notified
 *  - blocker: tries to enter a synchronized section while main holds it -> BLOCKED
 * We log each thread's state over time to see a full spectrum of transitions.
 */
public class LifecycleShowcaseApp
{
    static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread sleeper = new Thread(new Sleeper(), "sleeper");
        Thread waiter  = new Thread(new Waiter(),  "waiter");
        Thread blocker = new Thread(new Blocker(), "blocker");

        // Initially NEW
        printStates("before start", sleeper, waiter, blocker);

        sleeper.start(); waiter.start(); blocker.start();
        printStates("just started", sleeper, waiter, blocker);

        // Force BLOCKED: hold MONITOR while blocker tries to enter it
        synchronized (MONITOR) {
            TimeUnit.MILLISECONDS.sleep(250); // blocker attempts to enter -> BLOCKED
            printStates("main holding MONITOR", sleeper, waiter, blocker);
        }

        // Wake the waiter
        synchronized (MONITOR) {
            MONITOR.notifyAll();
        }

        // Observe for a short while
        for (int i = 0; i < 6; i++) {
            printStates("tick " + i, sleeper, waiter, blocker);
            TimeUnit.MILLISECONDS.sleep(150);
        }

        sleeper.join(); waiter.join(); blocker.join();
        printStates("after join", sleeper, waiter, blocker);
        System.out.println("Showcase complete.");
    }

    private static void printStates(String tag, Thread... ts) {
        Map<Thread.State, Integer> histogram = new EnumMap<>(Thread.State.class);
        for (Thread t : ts) histogram.merge(t.getState(), 1, Integer::sum);
        System.out.printf("%s | %s | ", Instant.now(), tag);
        for (Thread t : ts) System.out.printf("%s=%s  ", t.getName(), t.getState());
        System.out.print("| histogram: " + histogram);
        System.out.println();
    }

}
