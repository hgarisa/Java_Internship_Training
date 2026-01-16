package Deep_Threading.Thread_Pools.Q14;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BoundedPoolWithBackpressure
{
    public static void main(String[] args) throws InterruptedException {

        int cores = Runtime.getRuntime().availableProcessors();
        // This finds out how many CPU Cores your machine has .
        // From this , the pool will have exactly this many worker threads.
        // That means at most cores tasks will run in parallel.

        // ThreadFactory is an interface in java . Its job is to define how new threads are created in a thread pool.
        // Normally if you dont provide a ThreadFactory , Java uses a default factory that makes plain threads with generic names .
        // With a custom factory , you can control :
        // Thread Names( Easier for debugging)
        // Thread Priority
        // Whether they are daemon threads or not .
        // Even assign them to a specific thread group.

        ThreadFactory named = new ThreadFactory() // -> This creates an object that implements the ThreadFactory interface.
            // Instead of writing a whole class , it uses an anonymous inner class. So named is now your custom thread creator.
        {

            AtomicInteger n = new AtomicInteger(); // AtomicInteger is a thread-safe counter.
            // Every time a new thread is created , the counter increases.
           // It is used here to give each thread a unique number (1 , 2 , 3)
            @Override
            public Thread newThread(Runnable r) { // -> This method is called whenever the pool needs a new thread.
                //  It receives the Runnable r (which is the task to be run)
                // You return a new Thread object that will run this task.

                Thread t = new Thread(r , "io-worker-" + n.incrementAndGet());
                //  Creates a new thread that will execute r .
                // Sets the thread name to "io-worker-X"

                t.setDaemon(true);
                // Daemon thread = Background helper thread . Meaning that the JVM will not wait for daemon threads to finish when shutting down.
                // For example : Garbage collector runs on daemon threads.
                // Here, it means: “don’t block JVM shutdown if these worker threads are still running.”

                return t;
                // Returns the customized thread to the pool.
            }
        };

        // This is a bounded queue , as this queue can only hold 200 tasks waiting.
        // If you keep submitting tasks and all workers are busy , they get queued here .
        // This prevents unlimited task buildup(so there is no OutOfMemoryError)
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(200);


        ThreadPoolExecutor exec = new ThreadPoolExecutor(

                cores ,
                // This is the minimum number of threads that the pool will keep alive. Even if they are idle ,
                // they won't be destroyed. Here for instance, you could set to the number of CPU cores (say , 8)
                // That means that always keep 8 worker threads ready .
                cores ,

                // This cores again is the maximum number of threads allowed in a pool.
                // You set it equal to the core size -> as this is a fixed-size pool. So the pool always has exactly cores threads ,
                // no more , no less. This means we dont want dynamic growth , we just want a stable pool.
                0L, TimeUnit.SECONDS,
                // This how long extra (non-core) threads should wait idle before being killed.
                // But since core size = max size, you’ll never have “extra” threads.
                // So here it’s not really used (just 0 seconds).
                // Basically: irrelevant in fixed pools, but required by the constructor.
                queue,
                named,
                new ThreadPoolExecutor.CallerRunsPolicy() // Rejection policy
                // What happens if both the pool and queue are full?
                // If you choose CallerRunsPolicy -> The calling thread(in this case , the main) will run the tasks itself.
                // This slows down the producer, creating back-pressure instead of losing tasks or crashing.

        );

        for (int i = 0; i < 1000; i++) {
            int id = i;
            exec.submit(() -> {
                // simulate I/O
                try { TimeUnit.MILLISECONDS.sleep(50); } catch (InterruptedException ignored) {}

                System.out.println("Task " + id + " by " + Thread.currentThread().getName());
            });

        }
        exec.shutdown(); // It stops accepting new tasks , finishes queued ones.
        exec.awaitTermination(30 , TimeUnit.SECONDS); // Is when it waits up to 30 seconds for everything to finish.



    }
}
