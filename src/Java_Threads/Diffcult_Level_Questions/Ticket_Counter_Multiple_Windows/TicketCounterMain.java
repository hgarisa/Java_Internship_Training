package Java_Threads.Diffcult_Level_Questions.Ticket_Counter_Multiple_Windows;


/*
*
*
Problem Description
You have:

A ticket counter with a limited number of tickets (e.g., 10).

Multiple threads (representing different ticket windows) trying to sell these tickets at the same time.

 Goal
Ensure that:

Each ticket is sold exactly once.

No race condition occurs (e.g., two threads selling the same ticket).

Output shows which window sold which ticket.


* */
public class TicketCounterMain
{
    public static void main(String[] args)
    {

        TicketCounter counter = new TicketCounter();

        Thread window1 = new Thread(new TicketWindow(counter , " Window 1"));
        Thread window2 = new Thread(new TicketWindow(counter , " Window 2"));
        Thread window3 = new Thread(new TicketWindow(counter , " Window 3"));

        window1.start();
        window2.start();
        window3.start();

    }
}
