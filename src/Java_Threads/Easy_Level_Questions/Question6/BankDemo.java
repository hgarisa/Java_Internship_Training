package Java_Threads.Easy_Level_Questions.Question6;

public class BankDemo
{
    public static void main(String[] args)
    {

        Account account = new Account();

        Thread user1 = new Thread(() -> account.withdraw("User1" , 70));
        Thread user2 = new Thread(() -> account.withdraw("User2" , 50));

        user1.start();
        user2.start();
        System.out.println("Main thread Ending...");

    }
}
