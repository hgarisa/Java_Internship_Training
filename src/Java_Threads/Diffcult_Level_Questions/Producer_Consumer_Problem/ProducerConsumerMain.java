package Java_Threads.Diffcult_Level_Questions.Producer_Consumer_Problem;

public class ProducerConsumerMain
{
    public static void main(String[] args)
    {

        //  Create a shared Buffer with capacity of 5 items.

        SharedBuffer buffer = new SharedBuffer(5);

        // Create producer and consumer instances

        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        // Wrap them in threads

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);


        // Start both threads

        producerThread.start();
        consumerThread.start();


    }
}
