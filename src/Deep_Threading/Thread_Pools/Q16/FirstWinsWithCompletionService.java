package Deep_Threading.Thread_Pools.Q16;

import java.util.concurrent.*;

public class FirstWinsWithCompletionService
{
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService exec = Executors.newFixedThreadPool(3);
        // A pool of 3 threads → can run up to 3 tasks in parallel.

        CompletionService<String> ecs = new ExecutorCompletionService<>(exec);
        // CompletionService<String> -> Is a service that manages Callable<String> tasks and gives back
        // results (Strings) in finished order.

        // new ExecutorCompletionService<>(exec) -> Creates one service , backed by your existing thread pool.
        // and exec executes the tasks
        // ecs gives you a neat way to retrieve them as they complete


        ecs.submit( () -> fetch("eu" , 400) );
        ecs.submit( () -> fetch("us" , 250) ); // fastest
        ecs.submit( () -> fetch("ap" , 350) );

        String winner = ecs.take().get(); // First completed task
        System.out.println(" Winner : " + winner);

        // Optional: cancel remaining tasks
        exec.shutdownNow();

    }
    static String fetch(String region , long ms) throws InterruptedException {
        Thread.sleep(ms);
        return "data-from-" + region;

    }


}
