package Java_Threads.Diffcult_Level_Questions.ReentrantLock_And_CronSchedule;

import java.util.concurrent.locks.ReentrantLock;

public class ScheduledJob implements Runnable
{

    private final String jobName ;
    private final ResourceLock firstResource;
    private final ResourceLock secondResource;


    public ScheduledJob(String jobName, ResourceLock firstResource, ResourceLock secondResource) {
        this.jobName = jobName;
        this.firstResource = firstResource;
        this.secondResource = secondResource;
    }
    @Override
    public void run()
    {
        ReentrantLock firstSouceLock = firstResource.getLock();
        ReentrantLock secondSouceLock = secondResource.getLock();

        System.out.println(jobName + " is starting ");

        try {
            firstSouceLock.lock();
            firstResource.acquireLocks(jobName);
            Thread.sleep(3000);
            secondSouceLock.lock();
            secondResource.acquireLocks(jobName);
            Thread.sleep(3000);

        }
        catch (InterruptedException e) {
            System.out.println(jobName + " was interrupted ");
        }
        finally
        {
            firstSouceLock.unlock();
            firstResource.releaseLocks(jobName);
            secondSouceLock.unlock();
            secondResource.releaseLocks(jobName);
            System.out.println(jobName + " is finished ");
        }



    }

}





