package Java_IO.Networking.IoT_Sensors;
import java.io.*;
import java.net.*;

/*
*
 Scenario:
You're building a real-time monitoring system where multiple
* IoT sensors (clients) send live temperature and
* humidity data to a central dashboard server.
* The dashboard displays each incoming sensor reading in real-time.

* */

/*
 * Acts as the IoT central dashboard server.
 * Listens for sensor connections and prints their readings.
 */
public class SensorDashboardServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7000);
        System.out.println("Sensor Dashboard Server started on port 7000...");

        while (true) {
            Socket sensorSocket = serverSocket.accept();
            System.out.println("Sensor connected: " + sensorSocket.getInetAddress());

            new Thread(() -> {
                try (
                        BufferedReader reader = new BufferedReader(new InputStreamReader(sensorSocket.getInputStream()))
                ) {
                    String reading;
                    while ((reading = reader.readLine()) != null) {
                        System.out.println(reading);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

