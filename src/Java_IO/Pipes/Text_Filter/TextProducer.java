package Java_IO.Pipes.Text_Filter;
import java.io.OutputStream;
import java.io.PrintWriter;

/*
 * Producer thread: Sends messages through a pipe.
 */
public class TextProducer extends Thread
{
    private final OutputStream outputStream;

    public TextProducer(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        try (PrintWriter writer = new PrintWriter(outputStream)) {
            writer.println("Hello! This is a normal message.");
            writer.println("Oops, this message contains a badword.");
            writer.println("Have a nice day!");
            writer.println("Another badword sneaked in.");
        }
    }


}
