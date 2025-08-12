package Java_Threads.Easy_Level_Questions.volatileExamples;

public class WithVolatile
{
    static volatile boolean running = true;
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {

            while (running) {

                System.out.println(" Thread t1 is Running this statement while running is true");
            }
            System.out.println("Thread-stopped");

        });

        t1.start();
        Thread.sleep(1000); // Let the thread run for two seconds
        running = false ; // Now the main thread has set running to false
        System.out.println("Main thread has set running = false");


     // The worker thread always sees the updated value of running , and the loop stops as expected .

    }
}
