package Generics_Pratice_Questions.Payment_Processing;

public abstract class Payment
{

    public double amount ;

    public Payment(double amount)
    {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public abstract String getDetails();
}
