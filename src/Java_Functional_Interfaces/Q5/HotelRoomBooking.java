package Java_Functional_Interfaces.Q5;
import java.util.*;
import java.util.function.*;

// Hotel Room Booking with Functional Interface Chain
//        Scenario:
//        You are developing a hotel room reservation system where price calculation depends
//        on room type, season, and membership.
//
//        Task:
//        Create class Booking with fields:
//        bookingId, customerName, roomType, isPeakSeason, isMember, basePrice.

//        Use:

//        Function<Booking, Double> to apply peak season charges.
//
//        Function<Double, Double> to apply membership discounts.
//
//        UnaryOperator<Double> to apply tax.
//
//        Combine all functions together using Function.andThen() and Function.compose().
//

public class HotelRoomBooking {

    static class Booking {
        int bookingId;
        String customerName;
        String roomType;
        boolean isPeakSeason;
        boolean isMember;
        double basePrice;
        double finalPrice;

        public Booking(int bookingId, String customerName, String roomType, boolean isPeakSeason, boolean isMember, double basePrice) {
            this.bookingId = bookingId;
            this.customerName = customerName;
            this.roomType = roomType;
            this.isPeakSeason = isPeakSeason;
            this.isMember = isMember;
            this.basePrice = basePrice;
        }

        @Override
        public String toString() {
            return customerName + " | Room: " + roomType + " | Final Price: " + finalPrice;
        }
    }

    public static void main(String[] args) {

        List<Booking> bookings = Arrays.asList(
                new Booking(1, "Alex", "Deluxe", true, true, 200),
                new Booking(2, "Brian", "Standard", false, true, 150),
                new Booking(3, "Charlie", "Suite", true, false, 300)
        );

        Function<Booking, Double> applyPeakSeasonCharge = b -> b.isPeakSeason ? b.basePrice * 1.25 : b.basePrice;

        Function<Double, Double> applyMembershipDiscount = price -> price * 0.90;

        UnaryOperator<Double> applyTax = price -> price * 1.15;

        bookings.forEach(b -> {
            double price = applyPeakSeasonCharge.apply(b);
            if (b.isMember) {
                price = applyMembershipDiscount.apply(price);
            }
            price = applyTax.apply(price);
            b.finalPrice = price;
        });

        bookings.forEach(System.out::println);
    }

    // Booking contains reservation details.
// Function applies peak season charges.
// Another Function applies membership discount.
// UnaryOperator applies tax.
// Functional chaining is used to process the final price calculation.

}
