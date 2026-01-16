package Generics_Pratice_Questions.Notification_System;

public class SMSNotification extends Notification
{
    public SMSNotification(String message) {
        super(message);
    }

    @Override
    public void send()
    {
        System.out.println("Sending SMS :" + getMessage());
    }
}
