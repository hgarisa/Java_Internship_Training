package oops.Abstraction.QuestionOne;

// Abstract base class defining a template for payments
abstract class PaymentGateway {
    // Template method: defines payment steps
    public final void processPayment(double amount) {
        authenticateUser();         // Common to all gateways
        makePayment(amount);        // Varies per gateway
        generateReceipt();          // Common to all gateways
    }

    // Concrete method - shared by all implementations
    private void authenticateUser() {
        System.out.println("Authenticating user...");
    }

    // Abstract method to be implemented by each gateway
    protected abstract void makePayment(double amount);

    // Concrete method - shared by all
    private void generateReceipt() {
        System.out.println("Generating receipt...");
    }
}