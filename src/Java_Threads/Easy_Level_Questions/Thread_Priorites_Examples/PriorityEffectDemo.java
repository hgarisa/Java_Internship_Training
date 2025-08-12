package Java_Threads.Easy_Level_Questions.Thread_Priorites_Examples;

public class PriorityEffectDemo
{
    public static void main(String[] args)
    {
        Runnable printTask = () -> {


            for (int i = 0; i < 1000 ; i++)
            {

                System.out.println(Thread.currentThread().getName() + " - " + i);
            }
        };

        Thread low = new Thread(printTask ,"Low-Priority");
        Thread high = new Thread(printTask ,"High-Priority");


        low.setPriority(Thread.MIN_PRIORITY);
        high.setPriority(Thread.MAX_PRIORITY);

        low.start();
        high.start();


        // Usually  , HighPriority thread might print more lines before LowPriority due to higher priority.
        // but this is not guaranteed on all systems . Often on modern OSes , all threads get nearly equal cpu time .
    }
}
