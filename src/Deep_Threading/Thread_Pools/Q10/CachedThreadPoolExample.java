package Deep_Threading.Thread_Pools.Q10;

import java.util.concurrent.*;

// 2. CachedThreadPool
public class CachedThreadPoolExample
{
    public static void main(String[] args)
    {

        ExecutorService executor = Executors.newCachedThreadPool(); // grows/shrinks threads as needed

        for (int i = 1; i <= 5 ; i++)
        {
            int taskID = i;
            executor.submit(() -> {

                System.out.println(" Cached pool task " + taskID + " running on " + Thread.currentThread().getName());
            });
        }

        executor.shutdown();
    }
}
