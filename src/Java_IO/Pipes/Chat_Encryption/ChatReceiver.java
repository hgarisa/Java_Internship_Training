package Java_IO.Pipes.Chat_Encryption;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 * Receiver class: reads encrypted messages from the pipe and decrypts them.
 */
public class ChatReceiver extends Thread {
    private final InputStream inputStream;

    public ChatReceiver(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            System.out.println("Decrypted Chat:\n");

            while ((line = reader.readLine()) != null) {
                String decrypted = CaesarCipher.decrypt(line);
                if (decrypted.equalsIgnoreCase("exit")) break;
                System.out.println(decrypted);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

