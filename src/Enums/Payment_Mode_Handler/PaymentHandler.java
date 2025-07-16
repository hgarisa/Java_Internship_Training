package Enums.Payment_Mode_Handler;

public class PaymentHandler
{
    public void processPayment(Transaction txn) {
        System.out.println("Processing $" + txn.getAmount() + " via " + txn.getMethod());
        switch (txn.getMethod()) {
            case CREDIT_CARD, DEBIT_CARD -> System.out.println("Card payment successful.");
            case PAYPAL -> System.out.println("Paid via PayPal.");
            case BANK_TRANSFER -> System.out.println("Bank transfer completed.");
            case UPI -> System.out.println("UPI payment processed.");
        }
    }
}
