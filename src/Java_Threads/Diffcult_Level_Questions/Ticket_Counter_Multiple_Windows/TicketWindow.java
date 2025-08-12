package Java_Threads.Diffcult_Level_Questions.Ticket_Counter_Multiple_Windows;

public class TicketWindow implements Runnable
{


 private final TicketCounter counter;

 private final String windowName;

    public TicketWindow(TicketCounter counter, String windowName) {
        this.counter = counter;
        this.windowName = windowName;
    }


    @Override
    public void run()
    {
        while (true)
        {

            counter.sellTicket(windowName);

            try {
                Thread.sleep(2000); // Simulate the time taken to sell a ticket
            } catch (InterruptedException e)
            {

                System.out.println(windowName  + " was interrupted ");
                break;
            }


        }

    }
}
