package Java_Threads.Easy_Level_Questions.Question9;

public class SleepDemo
{
    public static void main(String[] args)
    {
        Thread sleepy = new Thread(() -> {

            try
            {
                System.out.println("Thread will sleep now...");
                Thread.sleep(3000);
                System.out.println("Thread woke up");
            }
            catch (InterruptedException e)
            {

                System.out.println("Interrupted while sleeping");
            }

        });

        sleepy.start();

    }
}
