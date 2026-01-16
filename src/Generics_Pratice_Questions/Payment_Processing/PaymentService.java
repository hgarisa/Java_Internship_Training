package Generics_Pratice_Questions.Payment_Processing;

public class PaymentService<T extends Payment>
{

    public PaymentProcessor<T> processor;
    public PaymentService(PaymentProcessor<T> processor)
    {

        this.processor = processor;
    }
    public void makePayment(T payment)
    {
        processor.processPayment(payment);

    }

}
