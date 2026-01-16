package Generics_Pratice_Questions.Notification_System;

import java.util.*;

public class NotificationUtils
{

   // Use ? extends for source (you can read from it)
    // use ? super for destination (you can write from it)

    public static <T> void copyNotifications(List<? extends T> source ,  List<? super  T> destination)
    {
        for (T notification : source)
        {
            destination.add(notification); // Safe to write
        }

    }

}
