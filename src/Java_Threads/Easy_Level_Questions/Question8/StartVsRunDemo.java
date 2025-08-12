package Java_Threads.Easy_Level_Questions.Question8;

public class StartVsRunDemo
{
    public static void main(String[] args)
    {

        Thread t1 = new Thread(() -> System.out.println("From run() method -> Thread : " + Thread.currentThread().getName()));

        System.out.println("Main Thread : " + Thread.currentThread().getName());
        t1.run();   // No new thread
      //  t1.start(); // New thread created

    }
}
