package Java_Threads.Diffcult_Level_Questions.Producer_Consumer_Problem;

public class Consumer implements Runnable
{

    private final SharedBuffer buffer;

    public Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run()
    {

        while (true)
        {
            try {
                int value = buffer.consume();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Consumer Interrupted");
            }

        }

    }
}
