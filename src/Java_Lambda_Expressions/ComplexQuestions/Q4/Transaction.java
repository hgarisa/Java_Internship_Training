package Java_Lambda_Expressions.ComplexQuestions.Q4;

public class Transaction
{
    public int transactionID;
    public String type ;

    public double amount;

    public Transaction(int TransactionID ,  String Type , double Amount  )
    {
        this.transactionID = TransactionID;
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

    @Override
    public String toString() {
        return "Transaction ID : " + transactionID + " Type : " + type + " Amount : " + amount;
    }


}
