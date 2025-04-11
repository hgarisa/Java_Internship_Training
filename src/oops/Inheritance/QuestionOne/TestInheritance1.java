package oops.Inheritance.QuestionOne;

public class TestInheritance1
{

    public static void main(String[] args) {
        // Create a PremiumSavingsAccount object
        PremiumSavingsAccount psa = new PremiumSavingsAccount("ACC123", 1000, 5, 50);

        psa.deposit(200);         // Inherited from Account
        psa.applyInterest();      // Inherited from SavingsAccount
        psa.applyCashback();      // Defined in PremiumSavingsAccount

        System.out.println("Final Balance: " + psa.getBalance());
    }
}
