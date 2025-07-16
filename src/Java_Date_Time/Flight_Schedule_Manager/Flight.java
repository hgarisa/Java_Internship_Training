package Java_Date_Time.Flight_Schedule_Manager;
import java.time.*;
import java.time.format.DateTimeFormatter;

/*
 * The `Flight` class represents a flight with its scheduling information.
 * It calculates the actual arrival time by taking into account:
 *  - the original departure time (with departure time zone),
 *  - the flight duration,
 *  - any delay (e.g., air traffic),
 *  - the arrival airport’s time zone.
 */
class Flight {
    private String flightNumber;
    private ZonedDateTime departureTime;  // Departure time including zone (e.g., New York)
    private Duration flightDuration;      // Duration of the flight (e.g., 7 hours)
    private Duration delay;               // Delay added to original schedule (e.g., 45 mins)
    private ZoneId arrivalZone;           // Destination time zone (e.g., Europe/London)


    public Flight(String flightNumber, String departureTimeStr, ZoneId departureZone, Duration flightDuration, Duration delay, ZoneId arrivalZone)
    {
        this.flightNumber = flightNumber;

        // Parse the string into LocalDateTime (without timezone)
        LocalDateTime localDT = LocalDateTime.parse(departureTimeStr);

        // Attach the departure time zone to form a ZonedDateTime
        this.departureTime = ZonedDateTime.of(localDT, departureZone);

        this.flightDuration = flightDuration;
        this.delay = delay;
        this.arrivalZone = arrivalZone;
    }

    /*
     * This method performs the key logic:
     * 1. Add flight duration to departure time.
     * 2. Add any delay to get updated arrival time.
     * 3. Convert that arrival time to the destination zone.
     * 4. Print the result in a readable format.
     */
    public void printArrivalDetails() {
        // Step 1: Add flight duration
        ZonedDateTime scheduledArrival = departureTime.plus(flightDuration);

        // Step 2: Add delay (if any)
        ZonedDateTime delayedArrival = scheduledArrival.plus(delay);

        // Step 3: Convert to destination time zone
        ZonedDateTime finalArrival = delayedArrival.withZoneSameInstant(arrivalZone);

        // Step 4: Print formatted result
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
        System.out.println("Flight " + flightNumber + " will arrive at: " + finalArrival.format(formatter));
    }

}
