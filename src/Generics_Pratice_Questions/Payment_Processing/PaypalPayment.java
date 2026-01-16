package Generics_Pratice_Questions.Payment_Processing;

public class PaypalPayment  extends Payment
{

    public String paypalID;
    public PaypalPayment(double amount  , String paypalID)
    {
        super(amount);
        this.paypalID = paypalID;
    }

    @Override
    public String getDetails() {
        return " Paypal ID : " + paypalID ;
    }


}
