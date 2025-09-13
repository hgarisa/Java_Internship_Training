package Deep_Threading.Ring_Buffer.Q3;

import java.util.concurrent.*;

public class ABQDemo
{
    public static void main(String[] args) throws InterruptedException
    {

        ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<>(8); // ring buffer of size 8

        Thread producer = new Thread(() -> {
            try
            {
                for (int i = 1 ; i <= 20 ; i++)
                {
                    q.put(i); // Blocks this if buffer size is full
                    System.out.println(" Thread 1 :  Produced " + i);
                }
            }
            catch (InterruptedException ignored)
            {}
        });

        Thread consumer = new Thread(() -> {
            try
            {
                for (int i = 1 ; i <= 20 ; i++)
                {
                    Integer v = q.take(); // Blocks this if buffer is empty
                    System.out.println(" Thread 2 :  Consumed " + i);
                }
            }
            catch (InterruptedException ignored)
            {}
        });

        Thread producer2 = new Thread(() -> {
            try
            {
                for (int i = 1 ; i <= 20 ; i++)
                {
                    q.put(i); // Blocks this if buffer size is full
                    System.out.println(" Thread 3 :  Produced " + i);
                }
            }
            catch (InterruptedException ignored)
            {}
        });

        Thread consumer2 = new Thread(() -> {
            try
            {
                for (int i = 1 ; i <= 20 ; i++)
                {
                    Integer v = q.take(); // Blocks this if buffer is empty
                    System.out.println(" Thread 4 : Consumed " + i);
                }
            }
            catch (InterruptedException ignored)
            {}
        });


        producer.start();
        producer2.start();
        consumer.start();
        consumer2.start();
        producer.join();
        producer2.join();
        consumer.join();
        consumer2.join();


    }
}
