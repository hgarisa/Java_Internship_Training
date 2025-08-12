package Java_Threads.Easy_Level_Questions.Question10;

public class JoinDemo
{
    public static void main(String[] args) throws InterruptedException
    {


        Thread t1 = new Thread(() -> {

            try
            {
                Thread.sleep(4000);
                System.out.println("T1-Done");
            }
            catch (InterruptedException ie)
            {

                System.out.println("Interrupted");
            }

        });

        Thread t2 = new Thread(() -> System.out.println("T2-Done"));


        t1.start();
        t1.join(); // main thread waits here until t1 finishes

        t2.start(); // This starts after t1 is completed

        //System.out.println("Main-Thread-Done");

    }
}
