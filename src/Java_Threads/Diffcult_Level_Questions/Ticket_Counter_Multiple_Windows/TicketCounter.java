package Java_Threads.Diffcult_Level_Questions.Ticket_Counter_Multiple_Windows;

public class TicketCounter
{
    private int ticketsAvailable = 10 ;


    public synchronized void sellTicket(String windowName)
    {
        if (ticketsAvailable > 0)
        {
            System.out.println(windowName + " sold ticket number " + ticketsAvailable);
            ticketsAvailable--;
        }
        else
        {
            System.out.println(windowName + " found no tickets left ");
        }


    }


}
