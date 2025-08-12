package Java_Threads.Diffcult_Level_Questions.Scheduled_Tasks;

public class ResourceLock {
    private final String resourceName;
    private boolean isLocked = false;

    public ResourceLock(String resourceName) {
        this.resourceName = resourceName;
    }

    // Synchronized method to acquire the lock
    public synchronized void lock(String jobName) {
        while (isLocked) {
            try {
                System.out.println(jobName + " is waiting for " + resourceName);
                wait(); // Wait until the resource is available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(jobName + " was interrupted while waiting for " + resourceName);
                return;
            }
        }
        isLocked = true;
        System.out.println(jobName + " locked " + resourceName);
    }

    // Synchronized method to release the lock
    public synchronized void unlock(String jobName) {
        isLocked = false;
        System.out.println(jobName + " released " + resourceName);
        notify(); // Notify other waiting threads
    }

    public String getResourceName() {
        return resourceName;
    }
}