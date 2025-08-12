package Java_Threads.Easy_Level_Questions.Question4;

public class ThreadSleepDemo
{
    public static void main(String[] args) throws InterruptedException
    {

        Thread t = new Thread(() -> {
            for (int i = 1; i <= 3 ; i++)
            {
                System.out.println(" Running " + i);

                try{
                    Thread.sleep(3000);
                }
                catch (InterruptedException ie)
                {
                    System.out.println("Interrupted");
                }
            }
        });

        t.start();
        t.join(); // Main thread waits for t to finish
        System.out.println("Main Thread ending");

    }
}
