package Java_Concurrency.Q1;
import java.util.concurrent.*;
import java.util.*;

 // Parking Lot System with Semaphore
//        Implement a parking lot where only a limited number of cars can park simultaneously.

// Main class simulates parking lot management
public class ParkingLot {

    // Semaphore controls how many cars can park concurrently
    private static final Semaphore semaphore = new Semaphore(3);

    static class Car implements Runnable {
        private final String carName;

        public Car(String carName) {
            this.carName = carName;
        }

        @Override
        public void run() {
            try {
                System.out.println(carName + " waiting for parking slot...");
                semaphore.acquire();
                System.out.println(carName + " parked.");
                Thread.sleep(2000);  // Simulate time spent parking
                System.out.println(carName + " leaving parking slot.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(6);

        // Simulating multiple cars trying to park
        for (int i = 1; i <= 6; i++) {
            executor.submit(new Car("Car " + i));
        }

        executor.shutdown();
    }

    /*

 Semaphore limits concurrent access to 3 cars.
Real-world: Parking system management.


    * */
}
