
/*


Hotel Reservation System
Problem:
Design a hotel booking system where:

Each Hotel has many Rooms.

Each Room has a Reservation history.

Use nested classes:

Room as inner class inside Hotel.

Reservation as static nested class inside Room.



* */

package Java_Nested_Classes.Q3;

import java.time.LocalDate;
import java.util.*;

// Main class: Hotel
public class Hotel {

    private String hotelName;
    private List<Room> rooms;

    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void displayHotelInfo() {
        System.out.println("Hotel Name: " + hotelName);
        for (Room room : rooms) {
            room.displayRoomInfo();
        }
    }

    // Inner Class: Room
    public class Room {
        private String roomNumber;
        private String roomType;
        private List<Reservation> reservations;

        public Room(String roomNumber, String roomType) {
            this.roomNumber = roomNumber;
            this.roomType = roomType;
            this.reservations = new ArrayList<>();
        }

        public void addReservation(Reservation res) {
            reservations.add(res);
        }

        public void displayRoomInfo() {
            System.out.println("Room: " + roomNumber + " (" + roomType + ")");
            for (Reservation res : reservations) {
                res.displayReservation();
            }
        }

        // Static Nested Class: Reservation
        public static class Reservation {
            private String reservationId;
            private String guestName;
            private LocalDate checkIn;
            private LocalDate checkOut;

            public Reservation(String reservationId, String guestName, LocalDate checkIn, LocalDate checkOut) {
                this.reservationId = reservationId;
                this.guestName = guestName;
                this.checkIn = checkIn;
                this.checkOut = checkOut;
            }

            public void displayReservation() {
                System.out.println("\tReservation: " + reservationId + ", Guest: " + guestName +
                        ", CheckIn: " + checkIn + ", CheckOut: " + checkOut);
            }
        }
    }


    public static void main(String[] args) {
        Hotel hotel = new Hotel("Grand Palace");

        Room room101 = hotel.new Room("101", "Deluxe");
        Room.Reservation res1 = new Room.Reservation("R001", "John", LocalDate.now(), LocalDate.now().plusDays(2));
        room101.addReservation(res1);

        hotel.addRoom(room101);
        hotel.displayHotelInfo();
    }
}
