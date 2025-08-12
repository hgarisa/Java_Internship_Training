package Java_Threads.Diffcult_Level_Questions.ReentrantLock_And_CronSchedule;


import java.util.concurrent.locks.ReentrantLock;

public class ResourceLock {
    private final String resourceName;
    ReentrantLock lock = new ReentrantLock();

    public ResourceLock(String resourceName) {
        this.resourceName = resourceName;
    }


    public void acquireLocks(String jobName)
    {

        System.out.println(jobName + " has locked " + resourceName);
    }

    public String getResourceName() {
        return resourceName;
    }

    public ReentrantLock getLock() {
        return lock;
    }


    public void releaseLocks(String jobName)
    {
        System.out.println(jobName + " has  released " + resourceName);
    }
}
