package Java_Threads.Easy_Level_Questions.Thread_Priorites_Examples;

public class ThreadPriorityDemo
{
    public static void main(String[] args)
    {
        Thread t1 = new Thread(() -> System.out.println("Thread 1"));
        Thread t2 = new Thread(() -> System.out.println("Thread 2"));
        Thread t3 = new Thread(() -> System.out.println("Thread 3"));

        t1.setPriority(Thread.MIN_PRIORITY); // 1
        t2.setPriority(Thread.NORM_PRIORITY); // 5
        t3.setPriority(Thread.MAX_PRIORITY); // 10

        System.out.println(" Priority of t1 : " + t1.getPriority());
        System.out.println(" Priority of t2 : " + t2.getPriority());
        System.out.println(" Priority of t3 : " + t3.getPriority());

        t1.start();
        t2.start();
        t3.start();


    }
}
