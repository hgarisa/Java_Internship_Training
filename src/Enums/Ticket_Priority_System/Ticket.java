package Enums.Ticket_Priority_System;

public class Ticket
{
    private String id;
    private Priority priority;
    private SupportAgent agent;

    public Ticket(String id, Priority priority, SupportAgent agent) {
        this.id = id;
        this.priority = priority;
        this.agent = agent;
    }

    public void handle() {
        System.out.println("Agent: " + agent.getAgentName() + " is handling ticket: " + id);
        switch (priority) {
            case LOW -> System.out.println("Respond within 72 hours.");
            case MEDIUM -> System.out.println("Respond within 48 hours.");
            case HIGH -> System.out.println("Respond within 24 hours.");
            case CRITICAL -> System.out.println("Respond immediately!");
        }
    }



}
