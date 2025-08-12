package Java_Threads.Easy_Level_Questions.Question5;

public class WithoutSyncDemo
{
    public static void main(String[] args) throws InterruptedException
    {

            Counter counter = new Counter();

            Thread t1 = new Thread(() -> {

                for (int i = 0; i < 100 ; i++)
                {
                    counter.increment();
                    System.out.println(i + " thread 1 ");
                }
            });

        Thread t2 = new Thread(() -> {

            for (int i = 0; i < 100 ; i++)
            {
                counter.increment();
                System.out.println(i + " thread 2 ");
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        t1.start();
        t2.start();

     // t1.join();
    //  t2.join();

        System.out.println("Final count (expected 200): " + counter.count);

    }
}
