package Java_Threads.Thread_Creation.Greeting_Printer;

public class GreetingApp
{
    public static void main(String[] args) {
        // Create and start two custom thread objects
        GreetingThread t1 = new GreetingThread("Hello");
        GreetingThread t2 = new GreetingThread("Welcome");

        t1.start();
        t2.start();
    }

}
