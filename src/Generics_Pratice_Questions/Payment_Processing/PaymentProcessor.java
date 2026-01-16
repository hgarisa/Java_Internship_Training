package Generics_Pratice_Questions.Payment_Processing;

public interface PaymentProcessor <T extends Payment>
{

    void processPayment(T payment);

}
