package Java_Threads.Diffcult_Level_Questions.ReentrantLock_with_tryLock_Timeout;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TransferTask implements Runnable
{
    private final Account fromAccount;
    private final Account toAccount;
    private final double amount ;

    public TransferTask(Account fromAccount, Account toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }
    @Override
    public void run()
    {
        ReentrantLock fromLock = fromAccount.getLock();
        ReentrantLock toLock = toAccount.getLock();


        boolean fromAccountLocked = false;
        boolean toAccountLocked = false;

        // Try to acquire both locks with a timeout of 2 seconds
        try {
            fromAccountLocked = fromLock.tryLock(2 , TimeUnit.SECONDS);
            if (fromAccountLocked)
            {
                toAccountLocked = toLock.tryLock(2, TimeUnit.SECONDS);
                    if (toAccountLocked){
                        // Both Locks Acquired
                        if (fromAccount.getBalance() >= amount) {
                            fromAccount.withdraw(amount);
                            toAccount.deposit(amount);
                            System.out.println(Thread.currentThread().getName() + " transferred " + amount + " from " + fromAccount.getAccountName() + " to " + toAccount.getAccountName());
                        } else {
                            System.out.println(Thread.currentThread().getName() + " failed to transfer : insufficient funds ");
                        }
                    }
                    else
                    {
                        System.out.println(Thread.currentThread().getName() + " could not lock the " + toAccount.getAccountName());
                    }
            }
            else
            {
                System.out.println(Thread.currentThread().getName() + " could not lock the " + fromAccount.getAccountName());
            }
        }
        catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted ");
        }
        finally {
            if (toAccountLocked)
            {
                toLock.unlock();
            }
            if (fromAccountLocked)
            {
                fromLock.unlock();
            }

        }

    }
}
