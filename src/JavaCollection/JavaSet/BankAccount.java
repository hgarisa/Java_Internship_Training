package JavaCollection.JavaSet;

public class BankAccount
{

    private String accountNumber;
    private String accountHolder;

    public BankAccount(String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
    }

    // getters
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof BankAccount)) return false;
        BankAccount that = (BankAccount) o;
        return this.accountNumber.equals(that.accountNumber); // Unique by account number
    }


    public int hashCode()
    {
        return this.accountNumber.hashCode();
    }

    public String toString()
    {

        return "BankAccount{accountNumber='" + accountNumber + "', accountHolder='" + accountHolder + "'}";

    }


}
