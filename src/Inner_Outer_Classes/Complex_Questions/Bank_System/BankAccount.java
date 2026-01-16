package Inner_Outer_Classes.Complex_Questions.Bank_System;

public class BankAccount
{
    public String accountHolderName ;
    public double balance ;

    public BankAccount(String accountHolderName , double balance)
    {
        this.accountHolderName = accountHolderName;
        this.balance = balance;

    }
    public void showAccountDetails()
    {

        System.out.println("Account holder name is " + accountHolderName + " with the current account balance of " + balance);

    }

    public class DebitCard
    {

        public void withdraw(double amount)
        {

            if (balance < amount)
            {

                System.out.println("Current balance in the account is insufficient to withdraw the amount");
            }
            else {
                balance = balance - amount;
                System.out.println("Withdrawing " + amount + " using debit card");
                System.out.println("Remaining balance : " + balance);
            }
        }
    }

    public static class CreditCard
    {
        private double creditLimit;

        public CreditCard(double creditLimit)
        {

            this.creditLimit = creditLimit;

        }
        public void useCredit(double amount)
        {

            creditLimit = creditLimit - amount;
            System.out.println(" Using " + amount + " from Credit Card ");
            System.out.println(" Remaining Credit Limit: " + creditLimit);

        }
    }
}
