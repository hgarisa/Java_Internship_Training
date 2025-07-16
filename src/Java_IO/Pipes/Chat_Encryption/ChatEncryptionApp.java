package Java_IO.Pipes.Chat_Encryption;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
/*
 * Main class: connects the chat sender and receiver using piped streams.
 * Encrypts messages in real-time during transmission.
 */
public class ChatEncryptionApp {
    public static void main(String[] args) throws Exception {
        PipedOutputStream pipedOut = new PipedOutputStream();
        PipedInputStream pipedIn = new PipedInputStream(pipedOut);

        ChatSender sender = new ChatSender(pipedOut);
        ChatReceiver receiver = new ChatReceiver(pipedIn);

        sender.start();
        receiver.start();
    }
}

