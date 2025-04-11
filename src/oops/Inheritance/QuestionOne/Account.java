package oops.Inheritance.QuestionOne;

// Base class: Generic bank account
class Account {
    protected String accountNumber;
    protected double balance;

    public Account(String accNum, double bal) {
        this.accountNumber = accNum;
        this.balance = bal;
    }

    // Deposit money into account
    public void deposit(double amount) {
        balance += amount;
    }

    // Get current balance
    public double getBalance() {
        return balance;
    }
}
