package oops.Inheritance.QuestionOne;

// Derived class: Inherits from SavingsAccount and adds cashback
class PremiumSavingsAccount extends SavingsAccount {
    private double cashback;

    public PremiumSavingsAccount(String accNum, double bal, double rate, double cashback) {
        super(accNum, bal, rate); // Call constructor of SavingsAccount
        this.cashback = cashback;
    }

    // Add cashback to balance
    public void applyCashback() {
        balance += cashback;
    }
}
