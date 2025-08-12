package Java_Threads.Easy_Level_Questions.PaymentProcessingSystem;

public class PaymentSystem
{
    public static void main(String[] args)
    {

        Thread paymentThread = new Thread(() -> {

            System.out.println("Processing payment");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }

            System.out.println("Payment Processed successfully");

        });

        Thread recieptThread = new Thread(() -> {

            try {
                paymentThread.join();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
            System.out.println("Generating receipt");
            System.out.println("Thank you for your purchase");
        });

        paymentThread.start();
        recieptThread.start();

    }
}
