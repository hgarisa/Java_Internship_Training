package Java_Concurrency.Q3;
import java.util.concurrent.*;
import java.util.*;

// Flight Booking System with ExecutorService
// Simulate a flight booking system where multiple users can book tickets concurrently for the same flight.
// You must ensure thread-safety when multiple threads access seat availability.


public class Flight {

    // Flight class manages seat booking with proper synchronization
    private final int totalSeats;
    private int availableSeats;

    public Flight(int totalSeats) {
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    // Synchronized method to ensure thread safety during booking
    public synchronized boolean bookSeat(String passengerName) {
        if (availableSeats > 0) {
            availableSeats--;
            System.out.println("Seat booked for: " + passengerName + ", Remaining Seats: " + availableSeats);
            return true;
        } else {
            System.out.println("No seats available for: " + passengerName);
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Flight flight = new Flight(10);
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // Simulating multiple users trying to book concurrently
        for (int i = 1; i <= 15; i++) {
            final int id = i;
            executorService.submit(() -> {
                flight.bookSeat("Passenger " + id);
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }


    /*

   ExecutorService is used to simulate concurrent users.

  synchronized ensures only one thread can book at a time.

    Real-world: Ticket reservation system.

     */

}

