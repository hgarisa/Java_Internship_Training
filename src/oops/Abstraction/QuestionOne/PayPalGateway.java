package oops.Abstraction.QuestionOne;

// Concrete implementation for PayPal
class PayPalGateway extends PaymentGateway {
    @Override
    protected void makePayment(double amount) {
        System.out.println("Processing payment of $" + amount + " via PayPal.");
    }
}