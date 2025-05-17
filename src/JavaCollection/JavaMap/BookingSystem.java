package JavaCollection.JavaMap;

import java.util.*;
public class BookingSystem
{
    public static void main(String[] args)
    {

        // Create a Map to store bookingId -> FlightBooking

        Map<String , FlightBooking> bookings = new HashMap<>();

        // Add bookings
        bookings.put("BKG123" , new FlightBooking("Alice", "FL100", "12A"));
        bookings.put("BKG456" , new FlightBooking("Bob", "FL200", "8B"));
        bookings.put("BKG789", new FlightBooking("Charlie", "FL100", "14C"));

        // Retrieve a booking by ID


        String bookingID = "BKG123";

        FlightBooking booking = bookings.get(bookingID);
        System.out.println("Booking details for ID : " + bookingID + " are : " + booking);

        // Loop through all bookings
        System.out.println("\nAll Bookings:");

        for (String id : bookings.keySet())
        {
            System.out.println("ID : " + id + " => " + bookings.get(id));
        }



    }
}
