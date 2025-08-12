package Java_Threads.Easy_Level_Questions.Question13;

public class ThreadComboDemo
{
    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            System.out.println(" Thread " +  Thread.currentThread().getName() +  " started ");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e)
            {
                System.out.println("Interrupted");
            }

            System.out.println(" Thread " + Thread.currentThread().getName() + " finished " );
        });

        t.setName("Booking Thread");
        t.start();

        if (t.isAlive())
        {
            System.out.println("Waiting for " + t.getName());

        }
        t.join(); // main thread waits for t to complete

        System.out.println("Main thread continues....");
    }
}
