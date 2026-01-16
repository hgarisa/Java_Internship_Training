package Inner_Outer_Classes.Complex_Questions.E_Commerce;
import java.util.*;
/*

 E-Commerce Checkout with Local Inner Class for Discount Strategy
 Scenario:
You’re building a simple checkout system for an e-commerce site.
When a customer places an order, you want to calculate and apply a discount if the total exceeds a
certain threshold (e.g., ₹1000).

But — you want this logic to only exist inside the checkout method, because it’s not used anywhere else.
That’s the perfect use case for a local inner class.

 Requirements:
Create a class CheckoutSystem with a method processOrder(double totalAmount).

Inside processOrder(), define a local inner class DiscountCalculator with:

A method calculateDiscountedTotal():

If totalAmount > 1000, apply 10% discount.

Else, no discount.

From processOrder(), use DiscountCalculator to compute and print:

Original total

Discount amount (if any)

Final total after discount

Call processOrder() from main() with at least two values (e.g., 900 and 1500) to test both paths.

* */
public class Main
{
    public static void main (String [] args)
    {

        CheckoutSystem checkoutSystem = new CheckoutSystem();
        checkoutSystem.processOrder(2000.00);
        CheckoutSystem checkoutSystem2 = new CheckoutSystem();
        checkoutSystem2.processOrder(800.00);

    }
}
