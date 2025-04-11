package oops.Polymorphism.QuestionTwo;

public class Main
{

    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        // Calls the method matching the number and type of parameters
        processor.pay(100.0);                          // Calls method with 1 param
        processor.pay(150.0, "Credit Card");           // Calls method with 2 params
        processor.pay(200.0, "PayPal", "USD");         // Calls method with 3 params
    }
}
