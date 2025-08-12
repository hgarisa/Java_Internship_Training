package Java_Threads.Diffcult_Level_Questions.Print_in_Order;

public class Printer
{
    private int state = 1 ; // 1 = A , 2 = B , 3 = C

    public synchronized void printA() throws InterruptedException {

        while (state != 1)
        {
            wait();
        }
        System.out.print("A");
        state = 2 ;
        notifyAll();

    }

    public synchronized void printB() throws InterruptedException {

        while (state != 2)
        {
            wait();
        }
        System.out.print("B");
        state = 3 ;
        notifyAll();

    }

    public synchronized void printC() throws InterruptedException {

        while (state != 3)
        {
            wait();
        }
        System.out.print("C\n"); // \n means add a new line after C
        state = 1 ;  // Resets/Restarts  for next round if looping
        notifyAll();

    }

}
