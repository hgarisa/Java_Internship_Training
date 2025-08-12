package Java_Threads.Easy_Level_Questions.Question7;

import java.util.*;

import java.util.HashSet;
import java.util.Set;

public class MovieTheatre
{

    Set<Integer> availableSeats;



    public MovieTheatre()
    {
        this.availableSeats = new HashSet<>(Arrays.asList(1 ,2 ,  3 , 4 , 5 , 6));

    }

    public synchronized void bookSeat(String personName , int seatnumber)
    {

        if (availableSeats.contains(seatnumber))
        {
            availableSeats.remove(seatnumber);
            try
            {
                System.out.println(personName + " has booked seat number " + seatnumber);
                Thread.sleep(5000);
            } catch (Exception e) {

                System.out.println(" Interrupted ");
            }

        }
        else
        {

            System.out.println("Invalid booking  , seat number has already been booked");
        }
    }
}
