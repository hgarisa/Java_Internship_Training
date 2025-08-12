package Java_Threads.Easy_Level_Questions.volatileExamples;

public class VolatileStopExample
{
    static volatile  boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {

            while (!stop)
            {
                System.out.println("Working.....");

//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    System.out.println("Thread has been interrupted");
//                }
            }
            System.out.println("Worker__Stopped working");
        });

        worker.start();
        Thread.sleep(1000); // Let the worker run for 1 second
        stop = true; // Tells the worker to stop
        System.out.println("Stop flag set to true");
    }

}
