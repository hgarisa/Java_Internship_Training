package oops.Encapsulation.QuestionOne;

// Class encapsulates sensitive data: balance, account number
public class BankAccount
{

    private String accountNumber;   // Private: cannot be accessed directly
    private double balance;

    public BankAccount(String accNum, double initialDeposit) {
        this.accountNumber = accNum;

        // Initial deposit must be positive
        if (initialDeposit > 0) {
            this.balance = initialDeposit;
        } else {
            throw new IllegalArgumentException("Initial deposit must be positive.");
        }
    }

    // Public getter (read-only) for account number
    public String getAccountNumber() {
        return accountNumber;
    }

    // Getter for balance (read-only to the outside)
    public double getBalance() {
        return balance;
    }

    // Public method to modify balance safely
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount; // Only positive amounts allowed
        } else {
            System.out.println("Deposit must be greater than zero.");
        }
    }

    // Withdraw with validations
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }
}
