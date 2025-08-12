package Java_Threads.Easy_Level_Questions.volatileExamples;

public class WithoutVolatile
{
      boolean running = true;

    public  void mainTesting() throws InterruptedException {
        Thread t1 = new Thread(() -> {

            while (running)
            {

                System.out.println(" Thread t1 is Running this statement while running is true");
            }

            System.out.println("Thread-stopped");
        });


        t1.start();

        Thread.sleep(1000); // Let the thread run for two seconds
        running = false ; // Now the main thread has set running to false
        System.out.println("Main thread has set running = false");


        // What might happen sometimes , is that , the other thread never sees the change to running ,

        // and the loop never stops . This is because it might be using a cached value .



    }
}
