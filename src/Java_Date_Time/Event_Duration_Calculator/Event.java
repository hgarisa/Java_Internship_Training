package Java_Date_Time.Event_Duration_Calculator;
import java.time.*;

/*
 * The `Event` class models an international event such as a global webinar,
 * meeting, or virtual conference. It stores the start and end times in
 * different time zones and calculates the actual event duration in hours and minutes.
 */
public class Event
{
    private String title;
    private ZonedDateTime start;
    private ZonedDateTime end;

    public Event(String title, ZonedDateTime start, ZonedDateTime end)
    {
        this.title = title;
        this.start = start;
        this.end = end;
    }

/*
 * This method calculates the duration of the event by:
 * 1. Converting both start and end times to `Instant` (UTC-based time)
 * 2. Calculating the time difference using `Duration.between(...)`
 * 3. Printing the result in a readable format (hours + minutes)
 */
    public void printDuration() {
        System.out.println(" Event Title: " + title);
        System.out.println("Start Time: " + start);
        System.out.println("End Time:   " + end);

        // Step 1: Convert start and end to UTC (Instant)
        Instant startInstant = start.toInstant();
        Instant endInstant = end.toInstant();

        // Step 2: Calculate the duration between start and end
        Duration duration = Duration.between(startInstant, endInstant);

        // Step 3: Extract hours and minutes and print the result
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();

        System.out.println(" Total Duration: " + hours + " hours and " + minutes + " minutes.");
    }

}
