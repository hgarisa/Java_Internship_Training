package Java_IO.Networking.Weather_API_Simulator;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * WeatherServer:
 * - Listens on port 6000.
 * - Waits for city name from the client.
 * - Responds with generated weather data.
 */
public class WeatherServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6000);
        System.out.println("Weather Server started on port 6000");



        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected from " + clientSocket.getInetAddress());

            new Thread(() -> {
                try (
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
                ) {
                    String city = in.readLine();
                    System.out.println("Received request for: " + city);

                    String weather = WeatherDataGenerator.generate(city);
                    out.println(weather);
                    System.out.println("Sent → " + weather);

                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}

