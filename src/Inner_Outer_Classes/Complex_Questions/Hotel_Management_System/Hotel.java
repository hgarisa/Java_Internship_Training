package Inner_Outer_Classes.Complex_Questions.Hotel_Management_System;

import java.util.Date;
public class Hotel
{
    String hotelName;
    public Hotel(String hotelName)
    {

        this.hotelName = hotelName;
    }

     public static class RoomType
    {
        String typeName;
        double pricePerNight;
        int capacity ;

        public RoomType(String typeName , double pricePerNight , int capacity )
        {
            this.typeName = typeName;
            this.pricePerNight = pricePerNight;
            this.capacity = capacity;

        }
        public void showRoomDetails()
        {
            System.out.println("Room type is " + typeName + " with price per night being : " + pricePerNight + " and the capacity of the room is " + capacity);
        }

    }

     public class Booking
    {
        String customerName;
        String checkInDate;
        RoomType roomType  ;


        public Booking(String customerName , String checkInDate , RoomType roomType)
        {
            this.customerName = customerName;
            this.checkInDate = checkInDate;
            this.roomType = roomType;
        }

        public void printReceipt()
        {
            System.out.println("Booking Receipt : ");
            System.out.println(" Hotel: " + hotelName);
            System.out.println("Customer:" + customerName);
            System.out.println(" Room : " + roomType.typeName);
            System.out.println("Price : " + roomType.pricePerNight);
            System.out.println("Check-In Date:" + checkInDate);

        }


    }



}
