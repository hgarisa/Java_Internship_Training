package Java_Lambda_Expressions.ComplexQuestions.Q3;

public class Invoice
{

    public int invoiceID ;
    public int customerID ;
    public double amount ;
    public String status ;

    public Invoice(int InvoiceID , int CustomerID  , double Amount , String Status )
    {
        this.invoiceID = InvoiceID;
        this.customerID = CustomerID;
        this.amount = Amount;
        this.status = Status;

    }

    public double getAmount() {
        return amount;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return " Invoice ID : " + invoiceID + " Customer ID : " + customerID + " Amount : " + amount
                + " Status :  " + status;
    }


}
