package oops.Encapsulation.QuestionThree;

public class TestEncapsulation3
{

    public static void main(String[] args) {
        Product p1 = new Product("P1001", "Wireless Mouse", 50);

        p1.sell(10);                     // Sell 10 units
        p1.restock(20);                  // Add 20 units to stock

        System.out.println("Product ID: " + p1.getProductId()); // Cannot change it
        System.out.println("Current Stock: " + p1.getStock());
    }

}
