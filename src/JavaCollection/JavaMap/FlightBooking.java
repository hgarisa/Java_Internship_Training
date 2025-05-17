package JavaCollection.JavaMap;

public class FlightBooking
{

    private String passengerName;
    private String flightNumber;
    private String seatNumber;


    public FlightBooking(String passengerName, String flightNumber, String seatNumber) {
        this.passengerName = passengerName;
        this.flightNumber = flightNumber;
        this.seatNumber = seatNumber;
    }


    // getters

    public String getPassengerName() {
        return passengerName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }


    public String toString() {
        return "Passenger: " + passengerName + ", Flight: " + flightNumber + ", Seat: " + seatNumber;
    }






}
