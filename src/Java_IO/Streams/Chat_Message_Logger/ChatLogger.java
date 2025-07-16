package Java_IO.Streams.Chat_Message_Logger;
import java.io.*;

/*
 * Handles logging chat messages to a file
 * and reading the entire chat history.
 */
public class ChatLogger
{
    private final File logFile = new File("chat_history.txt");

    /*
     * Appends a message to the log file with the sender’s name.
     */
    public void logMessage(User user, String message) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write(user.getUsername() + ": " + message);
            writer.newLine();
        }
    }

    /*
     * Reads and prints the complete chat history.
     */
    public void displayChatHistory() throws IOException {
        System.out.println("\n Chat History:");
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }


}
