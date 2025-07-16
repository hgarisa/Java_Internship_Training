package Java_IO.Networking.Multiplayer_Game;
import java.io.*;
import java.net.*;
import java.util.*;
/*
 * Handles the game lobby server.
 * Accepts clients and broadcasts chat messages to all participants.
 */
public class LobbyServer {
    private static final int PORT = 6000;
    private static final Set<PrintWriter> clientWriters = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Game Lobby Server started on port " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(() -> handleClient(clientSocket)).start();
        }
    }

    private static void handleClient(Socket socket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            clientWriters.add(out);
            String username = in.readLine(); // first message is username

            broadcast(username + " joined the lobby");

            String msg;
            while ((msg = in.readLine()) != null) {
                broadcast(username + ": " + msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            clientWriters.removeIf(writer -> writer.checkError());
        }
    }

    private static void broadcast(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
        }
    }


}

