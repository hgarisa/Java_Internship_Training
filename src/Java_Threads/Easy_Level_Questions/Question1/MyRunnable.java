package Java_Threads.Easy_Level_Questions.Question1;

public class MyRunnable implements Runnable
{
    @Override
    public void run()
    {
        System.out.println("Runnable thread running");
    }

    public static void main(String[]args) throws InterruptedException {
        System.out.println("Main thread is still running");
        Thread t1 = new Thread(new MyRunnable());
        t1.start();
       // Thread.sleep(5000);
        System.out.println("Main thread second printout is still running");

    }


}
