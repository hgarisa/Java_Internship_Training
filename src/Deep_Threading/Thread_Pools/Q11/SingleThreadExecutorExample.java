package Deep_Threading.Thread_Pools.Q11;

import java.util.concurrent.*;
public class SingleThreadExecutorExample
{
    public static void main(String[] args)
    {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 4 ; i++)
        {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Single-thread task " + taskId + " running on " + Thread.currentThread().getName());
            });
        }

        executor.shutdown();
    }
}
