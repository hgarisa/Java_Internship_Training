package Java_Nested_Classes.Q2;

/*

Bank Account Management System
Problem:

Build a system to manage bank accounts.

Each BankAccount has:

An AccountHolder (with personal details).

A list of Transactions (deposits/withdrawals).

Use nested classes:

AccountHolder as inner class of BankAccount.

Transaction as static nested class of BankAccount.

*/

import java.util.*;
import java.time.LocalDate;

public class BankAccount
{


    private String accountNumber;
    private double balance;
    private AccountHolder holder;
    private List<Transaction> transactions;

    public BankAccount(String accountNumber, double balance, AccountHolder holder) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.holder = holder;
        this.transactions = new ArrayList<>();
    }

    // Method to add transaction
    public void addTransaction(Transaction txn) {
        if (txn.type.equals("DEPOSIT")) {
            balance += txn.amount;
        } else if (txn.type.equals("WITHDRAWAL")) {
            balance -= txn.amount;
        }
        transactions.add(txn);
    }

    // Method to display account info
    public void displayAccountSummary() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
        holder.displayHolder();
        System.out.println("Transactions:");
        for (Transaction txn : transactions) {
            txn.displayTransaction();
        }
    }

    // Inner class: AccountHolder (tightly tied to BankAccount)
    public class AccountHolder {
        private String holderName;
        private String email;
        private String phone;

        public AccountHolder(String holderName, String email, String phone) {
            this.holderName = holderName;
            this.email = email;
            this.phone = phone;
        }

        public void displayHolder() {
            System.out.println("Holder Name: " + holderName + ", Email: " + email + ", Phone: " + phone);
        }
    }

    // Static Nested Class: Transaction (independent object)
    public static class Transaction {
        private String transactionId;
        private String type;
        private double amount;
        private LocalDate transactionDate;

        public Transaction(String transactionId, String type, double amount, LocalDate date) {
            this.transactionId = transactionId;
            this.type = type;
            this.amount = amount;
            this.transactionDate = date;
        }

        public void displayTransaction() {
            System.out.println("\t" + type + " - " + amount + " on " + transactionDate);
        }
    }

    // Main method
    public static void main(String[] args) {
        BankAccount.AccountHolder holder = new BankAccount("123", 0, null)
                .new AccountHolder("Alice", "alice@email.com", "1234567890");
        BankAccount account = new BankAccount("123", 1000, holder);

        account.addTransaction(new BankAccount.Transaction("T1", "DEPOSIT", 500, LocalDate.now()));
        account.addTransaction(new BankAccount.Transaction("T2", "WITHDRAWAL", 200, LocalDate.now()));

        account.displayAccountSummary();
    }






}
