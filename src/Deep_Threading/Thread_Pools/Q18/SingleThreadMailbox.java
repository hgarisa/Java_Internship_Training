package Deep_Threading.Thread_Pools.Q18;

import java.util.concurrent.*;

public class SingleThreadMailbox
{
    public static void main(String[] args)
    {

        ExecutorService mailbox = Executors.newSingleThreadExecutor();

        /*//This creates a thread pool with just one worker thread.
        //All tasks submitted go into a queue and are executed one after another.
        //No two tasks will ever run at the same time in parallel.*/


        // All writes happen in order, never concurrently

        for (int i = 0 ; i < 5 ; i++)
        {
            int id = i;
            mailbox.submit(() ->  appendToLog(" Event- " + id ) );
        }

        mailbox.shutdown();

    }

    static void appendToLog(String msg)
    {
        System.out.println(" Append " + msg + " by " + Thread.currentThread().getName());
    }

    //Simulates writing to a log file or database.
    //Prints which task is being processed, and by which thread (the single worker thread).
}
