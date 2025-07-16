package Enums.Payment_Mode_Handler;

public class Transaction
{
    private double amount;
    private PaymentMethod method;

    public Transaction(double amount, PaymentMethod method) {
        this.amount = amount;
        this.method = method;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public double getAmount() {
        return amount;
    }
}
