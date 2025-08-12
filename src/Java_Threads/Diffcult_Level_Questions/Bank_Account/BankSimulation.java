package Java_Threads.Diffcult_Level_Questions.Bank_Account;

import java.util.ArrayList;
import java.util.List;

public class BankSimulation
{
    public static void main(String[] args) throws InterruptedException {

        BankAccount acc1 = new BankAccount(1, 4000);
        BankAccount acc2 = new BankAccount(2, 3300);
        BankAccount acc3 = new BankAccount(3, 6000);
        BankAccount acc4 = new BankAccount(4, 2500);


        List<BankAccount> bankAccountslist = new ArrayList<>();
        bankAccountslist.add(acc1);
        bankAccountslist.add(acc2);
        bankAccountslist.add(acc3);
        bankAccountslist.add(acc4);


        Thread t1 = new Thread(new TransferTask(acc1 , acc2 , 344)) ;
        Thread t2 = new Thread(new TransferTask(acc2 , acc3 , 414)) ;
        Thread t3 = new Thread(new TransferTask(acc3 , acc4 , 634)) ;
        Thread t4 = new Thread(new TransferTask(acc3 , acc2 , 634)) ;
        Thread t5 = new Thread(new TransferTask(acc4 , acc1 , 819)) ;

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        List<Thread> threadList = new ArrayList<>();
        threadList.add(t1);
        threadList.add(t2);
        threadList.add(t3);
        threadList.add(t4);
        threadList.add(t5);


        // Using the join() method to wait for all  threads to finish
        for (Thread t: threadList )
        {
            t.join();
        }

        // Print final balances for all accounts
        for (BankAccount account: bankAccountslist)
        {
            System.out.println(account);
        }




    }

}
