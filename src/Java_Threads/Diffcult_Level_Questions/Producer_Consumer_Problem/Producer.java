package Java_Threads.Diffcult_Level_Questions.Producer_Consumer_Problem;

public class Producer implements Runnable
{

private final SharedBuffer buffer;

    public Producer(SharedBuffer buffer)
    {
        this.buffer = buffer;
    }


    @Override
    public void run()
    {

        int value = 1 ;

        while (true)
        {

            try {
                buffer.produce(value++);
                Thread.sleep(500);
            }
            catch (InterruptedException e)
            {
                System.out.println("Product Interrupted");
            }
        }


    }
}
