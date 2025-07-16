package Java_IO.Streams.Chat_Message_Logger;
import java.io.IOException;
import java.util.Scanner;


/*
 Scenario
You're building a simple command-line chat system:

Every time a user sends a message, it is appended to a file called chat_history.txt.

When the user types exit, the system reads and displays the entire chat history.

You will use BufferedWriter and BufferedReader for efficient I/O.


* */

/*
 * Simulates a simple CLI-based chat logger.
 * The user types messages, which are saved and displayed later.
 */
public class ChatApp
{
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ChatLogger logger = new ChatLogger();

        System.out.print("Enter your name: ");
        String username = scanner.nextLine();
        User user = new User(username);

        System.out.println("Type your messages (type 'exit' to finish and view history):");

        while (true) {
            String msg = scanner.nextLine();
            if (msg.equalsIgnoreCase("exit")) break;

            logger.logMessage(user, msg);
        }

        // Show full chat history after user exits
        logger.displayChatHistory();
    }

}
