package Java_Threads.Easy_Level_Questions.Question1;

public class MyThread extends Thread
{
    public void run()
    {

        System.out.println("Thread is running");
    }

    public static void main(String[]args)
    {

        System.out.println("My own printout");
        MyThread t1 =  new MyThread();
        t1.start(); // starts the thread and calls run() method.
        System.out.println("My second printout");
    }

}
