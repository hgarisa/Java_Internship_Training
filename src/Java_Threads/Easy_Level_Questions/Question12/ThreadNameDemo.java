package Java_Threads.Easy_Level_Questions.Question12;

public class ThreadNameDemo
{
    public static void main(String[] args)
    {

        Thread t = new Thread(() ->
        {

            System.out.println("Running thread : " + Thread.currentThread().getName());
        });


        t.setName("MycustomThread");
        t.run();
        t.start();
    }
}
