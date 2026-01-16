package Deep_Threading.Thread_Pools.Q9;

import java.util.concurrent.*;
public class ThreadPoolExample
{
    public static void main(String[] args)
    {
        ExecutorService executor = Executors.newFixedThreadPool(3); // Thread Pool of 3 threads

        for (int i = 1; i <= 5 ; i++)
        {

            int taskID = i;
            executor.submit(() -> {
                System.out.println(" Task " + taskID + " executed by " + Thread.currentThread().getName());
            });
        }
        executor.shutdown(); // graceful shutdown

    }



}
