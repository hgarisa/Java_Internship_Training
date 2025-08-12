package Java_Threads.Diffcult_Level_Questions.Shared_Printer;


/*
 Problem Statement:
You have one shared printer, and multiple users (threads) want to print documents.
Each user submits print jobs, and the printer processes one job at a time in order.

Only one thread can print at a time.

Other threads wait until the printer is available.

Print jobs are processed FIFO (First In First Out).

You’ll implement:

A PrintQueue class that holds jobs.

A PrintJob class (just a wrapper for job name or ID).

A User class (Runnable) that submits jobs.
  */
public class SharedPrinterMain
{
    public static void main(String[] args)
    {
        // Step 1 : Create a shared print queue

        PrintQueue printQueue = new PrintQueue();

        // Step 2 : Create and start the printer thread

        Thread printerThread = new Thread(new Printer(printQueue)  , "Shared-Printer" );
        printerThread.start();

        // Step 3 : Create and start multiple user threads

        Thread user1 = new Thread(new User(printQueue , "Alice") , "Alice-Thread");
        Thread user2 = new Thread(new User(printQueue , "Bob") , "Bob-Thread");
        Thread user3 = new Thread(new User(printQueue , "Charlie") , "Charlie-Thread");


        user1.start();
        user2.start();
        user3.start();

    }
}
