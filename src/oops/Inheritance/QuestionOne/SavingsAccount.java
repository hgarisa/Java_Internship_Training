package oops.Inheritance.QuestionOne;

// Intermediate class: Inherits from Account and adds interest functionality
class SavingsAccount extends Account {
    protected double interestRate;

    public SavingsAccount(String accNum, double bal, double rate) {
        super(accNum, bal); // Call constructor of Account
        this.interestRate = rate;
    }

    // Apply interest to balance
    public void applyInterest() {
        balance += balance * interestRate / 100;
    }
}
