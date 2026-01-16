package Deep_Threading.Thread_Pools.Q15;

import java.util.*;
import java.util.concurrent.*;
public class TimeoutsAndCancellation
{
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService exec = Executors.newFixedThreadPool(4);

        List<Callable<String>> calls = List.of(
                () -> slowCall("A" , 150) ,
                () -> slowCall("B" , 1500) , // will exceed timeout
                () -> slowCall("C" , 300)
        );

        // List.of(__) here creates an immutable list containing 3 tasks.
        // Each task is a lambda implementing Callable<String>.


        List<Future<String>> futures = exec.invokeAll(calls , 800 , TimeUnit.MILLISECONDS);

        // exec.invokeAll(__) method tells the executor(ThreadPool) to run all the tasks in the
        // list(calls) at the same time.
        // The second and the third argument (800, TimeUnit.MILLISECONDS) mean: Wait at most for 800
        // milliseconds . Then cancel any tasks that don't finish in time.

        // Return value : List<Future<String>> , this gives back a list of Future Objects. So basically
        // one future for each task in the calls list. Each Future<String> represents the result of the task
        // that will be completed later.

        // For this specific code  it runs the 3 callables (A,B,C) in parallel.
        // It returns a list of 3 Future<String> objects.
        // Each Future corresponds to one task.
        // After 800 ms :
        // Tasks that finished -> Future.get() returns their result (eg, "ok-A")
        // Tasks that are running -> They are cancelled , and their future will throw CancellationException
        // If you try to get() the result.



        for (Future<String> f : futures)
        {
            try {
                System.out.println(" Result : " + f.get());
            }
            catch (CancellationException ce)
            {
                System.out.println("Cancelled due to timeout");
            }

        }

        exec.shutdown();
        //Gracefully shut down the executor once tasks are done.

    }

    static String slowCall(String name , long ms) throws InterruptedException {

        Thread.sleep(ms);
        return "ok-" + name ;
    };

}
