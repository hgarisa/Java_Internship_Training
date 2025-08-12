package Java_Threads.Easy_Level_Questions.Question14;

public class DuoThreads
{
    public static void main(String[]args) throws InterruptedException {

        Thread t1 = new Thread(()-> {

            System.out.println(" Start of " + Thread.currentThread().getName());
            try
            {
                Thread.sleep(4000);
            }
            catch (InterruptedException ie)
            {
                System.out.println("Interrupted");
            }

            System.out.println(" End of  " + Thread.currentThread().getName());
        });


        t1.setName("CustomThread1");

        Thread t2 = new Thread(()-> {

            System.out.println(" Start of " + Thread.currentThread().getName());
            try
            {
                Thread.sleep(4000);
            }
            catch (InterruptedException ie)
            {
                System.out.println("Interrupted");
            }

            System.out.println(" End of  " + Thread.currentThread().getName());
        });

        t2.setName("CustomThread2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();



    }


}
