package Java_Threads.Easy_Level_Questions.ThreeThreads;

public class ThreeThreads
{
    public static void main(String[] args) throws InterruptedException
    {


        Thread t1 = new Thread(() -> {
            System.out.println(" Completed : " + Thread.currentThread().getName());
        });

        Thread t2 = new Thread(() -> {

            System.out.println(" Completed : " + Thread.currentThread().getName());

        });


        Thread t3 = new Thread(() -> {
            System.out.println(" Completed : " + Thread.currentThread().getName());

        });

        t1.setName("First-Thread");
        t2.setName("Second-Thread");
        t3.setName("Third-Thread");

        t1.start();
        t1.join();
        t2.start();
        t3.start();


    }
}
