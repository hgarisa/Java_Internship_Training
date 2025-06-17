package Java_Lambda_Expressions.ComplexQuestions.Q8;

public class Transaction
{
    public int transactionID;
    public int customerID;
    public String type ;

    public double amount;



    public Transaction(int TransactionID , int CustomerID , String Type , double Amount  )
    {
        this.transactionID = TransactionID;
        this.customerID = CustomerID;
        this.type = Type;
        this.amount = Amount;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public int getCustomerID() {
        return customerID;
    }

    @Override
    public String toString() {
        return "Transaction ID : " + transactionID + " Customer ID : " + customerID + " Type : " + type + " Amount : " + amount;
    }

}
