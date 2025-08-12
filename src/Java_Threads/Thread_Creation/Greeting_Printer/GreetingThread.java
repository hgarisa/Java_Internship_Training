package Java_Threads.Thread_Creation.Greeting_Printer;

// Extending Thread: "what to do" + "how it runs" in one class
public class GreetingThread extends Thread
{
    private String message;

    public GreetingThread(String message) {
        this.message = message;
    }


    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(getName() + " : " + message + " (" + i + ")");
            try {
                Thread.sleep(500); // simulate pause between messages
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




}
