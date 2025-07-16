package Java_IO.Pipes.Word_Count_Processor;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
  Reads lines from a pipe and computes total word count.
 */
public class WordCountProcessor extends Thread {
    private final InputStream inputStream;
    public WordCountProcessor(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    @Override
    public void run() {
        int totalWords = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;

            while ((line = reader.readLine()) != null) {
                int wordCount = line.trim().split("\\s+").length;
                totalWords += wordCount;
                System.out.println("Line: \"" + line + "\" → Words: " + wordCount);
            }

            System.out.println("\nTotal Words in Document: " + totalWords);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

