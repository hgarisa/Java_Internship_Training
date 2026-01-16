package Deep_Threading.Thread_Pools.Q13;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkStealingPoolExample
{
    public static void main(String[] args)
    {
        ExecutorService executor = Executors.newWorkStealingPool();

        for (int i = 1; i <= 5 ; i++)
        {

            int taskID = i ;
            executor.submit(() -> {

                System.out.println(" Work Stealing task " + taskID + " is running on " + Thread.currentThread().getName() );

            });

           try
           {
               Thread.sleep(1000);
           } catch (InterruptedException exception)
           {
           }


        }

        executor.shutdown();

    }
}
