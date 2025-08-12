package Java_Threads.Diffcult_Level_Questions.Shared_Printer;

import java.util.LinkedList;
import java.util.Queue;

public class PrintQueue
{
    private final Queue<String> jobQueue = new LinkedList<>();

    public synchronized void addJob(String job)
    {
        jobQueue.add(job);
        System.out.println(Thread.currentThread().getName() + " submitted job " + job);
        notifyAll(); // Notify the printer thread
    }
    public synchronized void processJob() throws InterruptedException
    {
        while (jobQueue.isEmpty())
        {
            wait(); // It will wait for jobs to be available
        }

        String job = jobQueue.poll();
        System.out.println("Printer processing Job : " + job);
        Thread.sleep(3000);
        System.out.println("Printer completed job : " + job);
    }




}
