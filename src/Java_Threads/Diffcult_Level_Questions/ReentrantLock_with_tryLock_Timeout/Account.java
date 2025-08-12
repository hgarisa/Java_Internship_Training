package Java_Threads.Diffcult_Level_Questions.ReentrantLock_with_tryLock_Timeout;

import java.util.concurrent.locks.ReentrantLock;

public class Account
{
    private final String accountName;

    private double balance ;

    private final ReentrantLock lock = new ReentrantLock();

    public Account(String accountName , double balance)
    {
        this.accountName = accountName;
        this.balance = balance ;
    }
    public String getAccountName() {
        return accountName;
    }

    public ReentrantLock getLock() {
        return lock;
    }
    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount)
    {
        balance -= amount;

    }
    public void deposit(double amount)
    {
        balance += amount;
    }






}
