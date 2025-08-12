package Java_Threads.Diffcult_Level_Questions.Shared_Printer;

public class Printer implements Runnable
{
    private final PrintQueue printQueue;

    public Printer(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }


    @Override
    public void run() {

        try {
            while (true) {
                printQueue.processJob();
            }
        } catch (InterruptedException e) {
            System.out.println("Printer was interrupted");
         }
    }

}
