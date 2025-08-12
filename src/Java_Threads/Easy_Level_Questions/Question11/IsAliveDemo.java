package Java_Threads.Easy_Level_Questions.Question11;

public class IsAliveDemo
{
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {

                Thread.sleep(4000);
            }
            catch (InterruptedException ie)
            {

                System.out.println("Interrupted");
            }


            System.out.println("Thread-T1-finished");
        });

        //System.out.println("Main thread is done");

        System.out.println("Is thread alive before start ? " +t1.isAlive());
        t1.start();
        System.out.println("Is thread alive after start ? " + t1.isAlive());
        t1.join();
        System.out.println("Is thread alive after join ? "+ t1.isAlive() );

       // System.out.println("Main thread is done");

    }
}
