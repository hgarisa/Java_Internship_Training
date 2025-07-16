package Java_IO.Pipes.Text_Filter;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 * Consumer thread: Reads and filters messages from the pipe.
 */
public class TextConsumer extends Thread
{
    private final InputStream inputStream;
    public TextConsumer(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.toLowerCase().contains("badword")) {
                    System.out.println("[CLEAN] " + line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
