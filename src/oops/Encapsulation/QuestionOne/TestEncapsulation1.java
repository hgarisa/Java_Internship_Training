package oops.Encapsulation.QuestionOne;

public class TestEncapsulation1
{



    public static void main(String[] args) {
        BankAccount acc = new BankAccount("ACC001", 500.0);

        acc.deposit(200);                 // Valid deposit
        acc.withdraw(100);               // Valid withdrawal
        acc.withdraw(1000);              // Invalid (exceeds balance)

        System.out.println("Final Balance: " + acc.getBalance());
        System.out.println("Account Number: " + acc.getAccountNumber());
    }
}
