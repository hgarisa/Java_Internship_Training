package Inner_Outer_Classes.Complex_Questions.E_Commerce;

public class CheckoutSystem
{

    public void processOrder(double totalAmount)
    {
     class DiscountCalculator
        {
          private  double discountPrice;
        public void calculateDiscountedTotal()
        {

            if (totalAmount > 1000)
            {
                 discountPrice =  totalAmount * (0.1);
                System.out.println(" Original Total is " + totalAmount);
                System.out.println(" Discounted applied : " + discountPrice);
                System.out.println("Final Total after discount is : " + (totalAmount - discountPrice));
            }
            else
            {
                System.out.println("Original Total is : " + totalAmount);
                System.out.println("No Discount applied");
                System.out.println("Final Total : " + totalAmount);
            }
        }
        }

        DiscountCalculator discountCalculator = new DiscountCalculator();
     discountCalculator.calculateDiscountedTotal();

    }
}
