package Java_Date_Time.Event_Duration_Calculator;
import java.time.*;

/*
 * The `EventDurationCalculator` main class simulates a scenario
 * where an event is conducted across two countries in different time zones.
 * It calculates how long the event actually lasts, in real-world time.
 */
public class EventDurationCalculator
{
    public static void main(String[] args)
    {
        // Define the start time of the event in India (Asia/Kolkata)
        ZonedDateTime start = ZonedDateTime.of(
                LocalDateTime.of(2025, 7, 20, 18, 30),
                ZoneId.of("Asia/Kolkata")
        );

        // Define the end time of the event in Los Angeles (America/Los_Angeles)
        ZonedDateTime end = ZonedDateTime.of(
                LocalDateTime.of(2025, 7, 20, 10, 0),
                ZoneId.of("America/Los_Angeles")
        );

        // Create an event object
        Event event = new Event("Global Java Conference", start, end);

        // Compute and display the duration of the event
        event.printDuration();

    }

    /*
Program Flow Explanation:

Input:

Start time and zone (e.g., India at 6:30 PM IST)

End time and zone (e.g., USA at 10:00 AM PDT)

Event title

Processing:

Convert both start and end to Instant (represents UTC timestamps)

Use Duration.between(...) to calculate the difference

Extract and display the duration in hours and minutes

Output:

Start and end times (with zones)

Actual duration of the event (in real hours/minutes)


     */

}
