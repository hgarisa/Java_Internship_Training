package Java_Concurrency.Q4;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.*;

//    Banking System with ReadWriteLock
//        Implement a bank account system where multiple readers can check balance
//        but only one writer can update it.

// Main class simulates bank account with read/write concurrency
public class BankAccount {

    private double balance;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        lock.writeLock().lock();
        try {
            System.out.println("Depositing " + amount);
            balance += amount;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void withdraw(double amount) {
        lock.writeLock().lock();
        try {
            System.out.println("Withdrawing " + amount);
            balance -= amount;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public double getBalance() {
        lock.readLock().lock();
        try {
            return balance;
        } finally {
            lock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        Runnable readTask = () -> {
            System.out.println("Balance read: " + account.getBalance());
        };

        Runnable writeTask = () -> {
            account.deposit(200);
            account.withdraw(100);
        };

        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.submit(readTask);
        executor.submit(writeTask);
        executor.submit(readTask);
        executor.submit(writeTask);
        executor.shutdown();
    }

    /*

   ReadWriteLock allows multiple readers but single writer.
   Real-world: Banking systems, stock markets, inventory.

   */
}

