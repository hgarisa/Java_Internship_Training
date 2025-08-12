package Java_Threads.Diffcult_Level_Questions.Producer_Consumer_Problem;

import java.util.LinkedList;
import java.util.Queue;

public class SharedBuffer
{
    private final Queue<Integer> buffer = new LinkedList<>();

    private  final int capacity ;


    public SharedBuffer(int capacity) {
        this.capacity = capacity;
    }



    public synchronized void produce(int value) throws InterruptedException {
        while (buffer.size() == capacity)
        {
            wait();  // Wait until there's space

        }

        buffer.add(value);
        System.out.println("Produced :  " + value);


        notifyAll(); // Notify all customers
    }

    public synchronized int consume() throws InterruptedException {
        while (buffer.isEmpty())
        {
            wait(); // wait until there's an item
        }

        int value = buffer.poll();
        System.out.println("Consumed : " + value);

        notifyAll(); // Notifies all producers

        return value;

    }



}
