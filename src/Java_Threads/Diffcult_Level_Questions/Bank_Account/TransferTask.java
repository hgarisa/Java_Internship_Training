package Java_Threads.Diffcult_Level_Questions.Bank_Account;

public class TransferTask implements Runnable
{

     BankAccount fromAccount;
    BankAccount toAccount;

    int amount ;

    public TransferTask(BankAccount from , BankAccount to , int amount)
    {
        this.fromAccount = from;
        this.toAccount = to;
        this.amount = amount;

    }
    @Override
    public void run()
    {

        fromAccount.transferTo(toAccount , amount);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

    }


}
