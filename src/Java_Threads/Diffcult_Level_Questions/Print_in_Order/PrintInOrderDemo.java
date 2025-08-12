package Java_Threads.Diffcult_Level_Questions.Print_in_Order;

public class PrintInOrderDemo
{
    public static void main(String[] args) throws InterruptedException {
        Printer printer = new Printer();

        Thread threadA = new Thread(() ->
        {
            for (int i = 0; i < 3 ; i++) {

                try {
                    printer.printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(() -> {


            for (int i = 0; i < 3 ; i++) {
                try {
                    printer.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadC = new Thread(() -> {

            for (int i = 0; i < 3 ; i++)
            {
                try {
                    printer.printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        // You can start it in any order
        threadB.start();
        threadA.start();
        threadC.start();
        // Wait for all of them to finish
        threadA.join();
        threadB.join();
        threadC.join();

    }
}
