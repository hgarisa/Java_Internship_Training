package Java_Threads.Easy_Level_Questions.Question3;

public class LambdaThread
{
    public static void main(String[] args)
    {

        System.out.println("MAIN THREAD RUNNING");
       Runnable task = () -> System.out.println("Lambda thread running...");
       new Thread(task).start();
        System.out.println("Main thread second printout is still running");
    }
}
