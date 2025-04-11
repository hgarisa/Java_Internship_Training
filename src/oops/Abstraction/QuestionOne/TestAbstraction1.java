package oops.Abstraction.QuestionOne;

public class TestAbstraction1
{

    public static void main(String[] args) {
        PaymentGateway paypal = new PayPalGateway();
        PaymentGateway stripe = new StripeGateway();

        // Polymorphic behavior with shared template
        paypal.processPayment(100.0);
        stripe.processPayment(250.0);
    }

}
