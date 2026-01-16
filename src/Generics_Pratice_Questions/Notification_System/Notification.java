package Generics_Pratice_Questions.Notification_System;

public abstract class Notification
{
    public String message;

    public Notification(String message)
    {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public abstract void send();



}
