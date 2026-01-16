package Generics_Pratice_Questions.Notification_System;

public class EmailNotification extends Notification
{

    public EmailNotification(String message) {
        super(message);
    }

    @Override
    public void send()
    {
        System.out.println("Sending Email : " + getMessage());
    }


    public String toString()
    {
    return " Email Notification :  " + getMessage() ;
    }
}
