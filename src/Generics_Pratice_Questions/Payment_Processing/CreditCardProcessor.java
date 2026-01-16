package Generics_Pratice_Questions.Payment_Processing;


public class CreditCardProcessor implements PaymentProcessor<CreditCardPayment>
{
    @Override
    public void processPayment(CreditCardPayment payment)
    {

        System.out.println(" Processing credit card payment of : $" + payment.getAmount());
        System.out.println(" Details : " + payment.getDetails());
    }
}
