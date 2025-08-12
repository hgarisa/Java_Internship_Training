package Java_Threads.Diffcult_Level_Questions.Shared_Printer;

public class User implements Runnable
{

    private final PrintQueue printQueue;

    private final String userName;

    public User(PrintQueue printQueue, String userName)
    {
        this.printQueue = printQueue;
        this.userName = userName;
    }
    @Override
    public void run()
    {

        for (int i = 1; i <= 3 ; i++)
        {
            String job = userName + " _Document_ " + i ;

            printQueue.addJob(job);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(userName  + "was interrupted");
            }
        }

    }
}
