package Java_IO.Pipes.Chat_Encryption;

import java.io.OutputStream;
import java.io.PrintWriter;

/*
  Sender class: sends encrypted messages through the pipe.
 */

public class ChatSender extends Thread
{
    private final OutputStream outputStream;

    public ChatSender(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        try (PrintWriter writer = new PrintWriter(outputStream)) {
            String[] messages = {
                    "Hello Madam",
                    " The Meeting is at 3PM ",
                    " Can you please Send the report",
                    "exit"
            };

            for (String msg : messages) {
                String encrypted = CaesarCipher.encrypt(msg);
                writer.println(encrypted);
                writer.flush();
                Thread.sleep(300); // Simulate typing delay
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
