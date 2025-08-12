package Java_Threads.Easy_Level_Questions.Question15;

public class StopWatch
{
    public static void main(String[]args)
    {

        Counter counter = new Counter();

        Thread t1 = new Thread(() ->
        {

            for (int i = 0; i < 5 ; i++)
            {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                counter.increment();
                System.out.println( "Count : " + counter.count + " " + Thread.currentThread().getName());
            }

        });

        t1.start();


    }
}
