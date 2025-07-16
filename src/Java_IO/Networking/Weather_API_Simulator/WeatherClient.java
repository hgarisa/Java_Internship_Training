package Java_IO.Networking.Weather_API_Simulator;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/*
 * WeatherClient:
 * - Connects to WeatherServer.
 * - Sends city name.
 * - Receives and displays weather data.
 */
public class WeatherClient {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        try (
                Socket socket = new Socket("localhost", 6000);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            System.out.print("Enter city name: ");
            String city = scanner.nextLine();

            out.println(city); // Send city
            String weather = in.readLine();  // Receive weather info

            System.out.println("Weather Info → " + weather);
        }
    }
}
