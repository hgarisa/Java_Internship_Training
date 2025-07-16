package Enums.Payment_Mode_Handler;

public class PaymentMethodTest
{
    public static void main(String[] args) {
        Transaction txn = new Transaction(2500, PaymentMethod.UPI);
        new PaymentHandler().processPayment(txn);
    }
}
