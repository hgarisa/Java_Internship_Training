package Java_Threads.Diffcult_Level_Questions.Bank_Account;

public class BankAccount
{
    private int id;
    private double balance ;

    public BankAccount(int id , double initialbalance)
    {
        this.id = id;
        this.balance = initialbalance;
    }


    public synchronized void deposit(int amount)
    {

        balance = balance + amount ;
        System.out.println(" Deposited amount " + amount +" to account " + id);

    }
    public synchronized void withdraw(int amount)
    {
        if (balance >= amount)
        {
            balance = balance - amount;
            System.out.println(" Withdrew amount " + amount + " from account " + id);

        }
        else
        {

            System.out.println("Insufficient balance in account " + id);
        }
    }

    public double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public String toString()
    {
     return "Account balance is  : " + balance + " for account ID : " + id ;
    }

    public void transferTo(BankAccount targetAccount , int amount)
    {
        BankAccount firstLock = this.id < targetAccount.id ? this : targetAccount;
        BankAccount secondLock = this.id < targetAccount.id ? targetAccount : this;

        synchronized (firstLock)
        {
            synchronized (secondLock)
            {
                if(this.balance >= amount)
                {
                    this.withdraw(amount);
                    targetAccount.deposit(amount);
                    System.out.println(" Transferred " + amount + " from account " + this.id + " to account " + targetAccount.id);
                }
                else
                {
                    System.out.println("Transfer failed : Insufficient funds in account " + this.id);
                }
            }

        }

    }

}
