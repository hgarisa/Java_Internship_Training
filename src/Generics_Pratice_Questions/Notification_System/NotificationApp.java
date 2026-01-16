package Generics_Pratice_Questions.Notification_System;
/*

 Problem Statement:
You are designing a notification system that sends different types of messages such as:

EmailNotification

SMSNotification

PushNotification

These all extend a base class Notification.

You want to implement a method to copy notifications from one list (source) to another (destination). But:

Source can be a list of EmailNotification, SMSNotification, etc.

Destination must be able to accept Notification or any supertype of it (like Object).

* */

import java.util.*;

public class NotificationApp
{
    public static void main(String[] args)
    {


        List<EmailNotification> emailNotificationList = List.of(

                new EmailNotification("Welcome") ,
                new EmailNotification("Your receipt.")

        );

        List<Notification> generalList = new ArrayList<>();

       List<Object> objectList = new ArrayList<>();

// Copy to Notifications list

        NotificationUtils.copyNotifications(emailNotificationList , generalList);

// Copy to Object list
NotificationUtils.copyNotifications(emailNotificationList , objectList);


// Now lets send all notifications in generalList

        for (Notification n: generalList)
        {
            n.send();

        }
        System.out.println("Objects in object list");

        for (Object o : objectList )
        {

            System.out.println(o);
        }


    }
}
