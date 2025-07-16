package Java_IO.Pipes.Word_Count_Processor;
import java.io.OutputStream;
import java.io.PrintWriter;
/*
  Simulates a document editor that sends lines of text to a pipe.
 */
public class DocumentEditor extends Thread {
    private final OutputStream outputStream;
    public DocumentEditor(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        try (PrintWriter writer = new PrintWriter(outputStream)) {
            writer.println("Java is a powerful programming language.");
            writer.println("It supports multithreading and IO streams.");
            writer.println("Streams like pipes allow inter-thread communication.");
            writer.println("End of document");
            writer.flush();
        }
    }
}


