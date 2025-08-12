package Java_Threads.Diffcult_Level_Questions.Scheduled_Tasks;

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

        System.out.println(jobName + " is starting ");

        try {
            firstResource.lock(jobName);
            Thread.sleep(2000);
            secondResource.lock(jobName);
            System.out.println(jobName + " is doing work ");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(jobName + " was interrupted ");
        }
        finally
        {
            secondResource.unlock(jobName);
            firstResource.unlock(jobName);
            System.out.println(jobName + " is finished ");
        }

    }


}
