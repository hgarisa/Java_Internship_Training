package Java_IO.Networking.Multiplayer_Game;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/*
 * Game client that connects to the lobby server,
 * sends messages and listens for lobby broadcasts.
 */
public class LobbyClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 6000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        out.println(username); // Send username

        // Thread to listen for messages
        new Thread(() -> {
            String incoming;
            try {
                while ((incoming = in.readLine()) != null) {
                    System.out.println(incoming);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Main thread sends messages
        while (true) {
            String message = scanner.nextLine();
            System.out.println(message);
        }
    }
}

