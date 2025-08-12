package Java_Threads.Diffcult_Level_Questions.Scheduled_Tasks;

public class CronSchedulerSimulation {
    public static void main(String[] args) {

        // Create shared resources
        ResourceLock database = new ResourceLock("Database");
        ResourceLock fileSystem = new ResourceLock("FileSystem");

        // Job1 locks: Database then FileSystem
        ScheduledJob job1 = new ScheduledJob("Job-1", database, fileSystem);

        // Job2 locks: FileSystem then Database (reversed — potential for deadlock!)
        ScheduledJob job2 = new ScheduledJob("Job-2", fileSystem, database);

        // Start both jobs on separate threads
        Thread t1 = new Thread(job1);
        Thread t2 = new Thread(job2);

        t1.start();
        t2.start();
    }
}