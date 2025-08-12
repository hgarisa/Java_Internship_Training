package Java_Threads.Diffcult_Level_Questions.ReentrantLock_with_tryLock_Timeout;

public class TransferSimulationMain
{
    public static void main(String[] args) throws InterruptedException {
        // Create Two accounts

        Account accountA = new Account("A-Account" , 3500);
        Account accountB = new Account("B-Account" , 4000);

        // Create TransferTask threads that transfer between the accounts
        Thread t1 = new Thread(new TransferTask(accountA ,accountB , 200) , "T1-A-TO-B" );
        Thread t2 = new Thread(new TransferTask(accountB ,accountA , 500) ,"T2-B-TO-A" );
        Thread t3 = new Thread(new TransferTask(accountB ,accountA , 300) ,"T3-B-TO-A" );
        Thread t4 = new Thread(new TransferTask(accountA ,accountB , 700) ,"T4-A-TO-B" );


        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        // Final balances

        System.out.println("\n -- Final Balances ---");
        System.out.println(accountA.getAccountName() + " has  : " + accountA.getBalance());
        System.out.println(accountB.getAccountName() + " has  : " + accountB.getBalance());

    }
}
