package Generics_Pratice_Questions.Payment_Processing;

public class PaypalProcessor  implements PaymentProcessor<PaypalPayment>
{


    @Override
    public void processPayment(PaypalPayment payment)
    {
        System.out.println(" Processing PayPal payment of : $ " + payment.getAmount());
        System.out.println(" Details : " + payment.getDetails());

    }

}
