package Java_IO.Pipes.Word_Count_Processor;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/*
  Connects a simulated document editor to a word count processor via pipe.
 */
public class WordCountApp {
    public static void main(String[] args) throws Exception {
        PipedOutputStream pipedOut = new PipedOutputStream();
        PipedInputStream pipedIn = new PipedInputStream(pipedOut);

        DocumentEditor editor = new DocumentEditor(pipedOut);
        WordCountProcessor processor = new WordCountProcessor(pipedIn);

        editor.start();
        processor.start();
    }
}

