package Java_Date_Time.Flight_Schedule_Manager;
import java.time.*;

/*
 * Main class to test the Flight scheduling system.
 * Demonstrates real-world usage where a flight is delayed and crosses time zones.
 */
public class Main
{
    public static void main(String[] args)
    {
        // Create a flight from New York to London with:
        // - Departure at 10 AM NY time on July 12, 2025
        // - Duration: 7 hours
        // - Delay: 45 minutes

        Flight flight = new Flight(
                "AI101",
                "2025-07-12T10:00:00",
                ZoneId.of("America/New_York"),     // Departure zone
                Duration.ofHours(7),               // Flight duration
                Duration.ofMinutes(45),            // Delay
                ZoneId.of("Europe/London")         // Arrival zone
        );

        // Compute and display the actual arrival time
        flight.printArrivalDetails();

    }

    /*
Program Flow Summary:

Input:

Flight number

Departure time (in local time format)

Time zones (departure and arrival)

Duration and delay

Processing:

Parses and converts the departure time into a time zone-aware ZonedDateTime.

Adds flight duration and delay to compute the total travel time.

Converts the result to the arrival zone’s time.

Output:

Prints the actual arrival time in the correct destination time zone using a readable format like:

2025-07-12 22:45 BST

    * */
}
