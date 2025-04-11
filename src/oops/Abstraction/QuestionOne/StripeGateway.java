package oops.Abstraction.QuestionOne;

// Concrete implementation for Stripe
class StripeGateway extends PaymentGateway {
    @Override
    protected void makePayment(double amount) {
        System.out.println("Processing payment of $" + amount + " via Stripe.");
    }
}
