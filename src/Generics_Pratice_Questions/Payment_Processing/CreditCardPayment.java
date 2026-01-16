package Generics_Pratice_Questions.Payment_Processing;

public class CreditCardPayment extends Payment
{
    public String cardNumber;
    public CreditCardPayment(double amount , String cardNumber)
    {
        super(amount);
        this.cardNumber = cardNumber;
    }

    @Override
    public String getDetails() {
        return "Credit Card Number : " + cardNumber ;
    }
}
