package Inner_Outer_Classes.Complex_Questions.Hotel_Management_System;

public class Main
{
    public static void main(String[] args)
    {


        Hotel hotel = new Hotel("Monte Carlo");


        //  Create and store RoomType
        Hotel.RoomType roomType = new Hotel.RoomType("Standard" , 2000.00 , 3);
        roomType.showRoomDetails();

        System.out.println("---------------------------------------------------------------------------------------------");

        // Pass the same room to booking

        Hotel.Booking booking = hotel.new Booking("Harry" , "2021/11/11" , roomType);
        booking.printReceipt();


        System.out.println("------------------------------------------------------------------------------------");
//        Hotel.RoomType roomType = new Hotel.RoomType("Deluxe" , 4500.00 , 5);
//        roomType.showRoomDetails();


    }
}
