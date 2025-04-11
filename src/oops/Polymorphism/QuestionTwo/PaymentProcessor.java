package oops.Polymorphism.QuestionTwo;

// Payment processor that supports multiple ways to pay (overloaded methods)
class PaymentProcessor {

    // Default payment method
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using default method.");
    }

    // Pay with specified payment method (e.g., Credit Card)
    public void pay(double amount, String method) {
        System.out.println("Paid " + amount + " using method: " + method);
    }

    // Pay with specified method and currency
    public void pay(double amount, String method, String currency) {
        System.out.println("Paid " + amount + " " + currency + " using method: " + method);
    }
}
