package Java_Threads.Easy_Level_Questions.Question6;

public class Account
{
    private int balance = 100;


    public synchronized void withdraw(String threadName , int amount)
    {
        if (balance >= amount)
        {
            System.out.println(threadName + " is going to withdraw $ " + amount);

            try
            {
                Thread.sleep(3000);
            }
            catch (Exception e)
            {
                System.out.println(" Interrupted ");
            }
            balance = balance - amount;

            System.out.println(threadName + " completed withdrawal. Remaining balance is : " + balance);
        }
        else
        {
            System.out.println(threadName + " cannot withdraw. Insufficient funds ");
        }
    }

}
