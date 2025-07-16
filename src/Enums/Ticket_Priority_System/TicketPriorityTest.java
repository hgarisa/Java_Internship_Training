package Enums.Ticket_Priority_System;

public class TicketPriorityTest
{
    public static void main(String[] args) {
        SupportAgent agent = new SupportAgent("Tom");
        Ticket ticket = new Ticket("TCK1023", Priority.HIGH, agent);
        ticket.handle();
    }


}
