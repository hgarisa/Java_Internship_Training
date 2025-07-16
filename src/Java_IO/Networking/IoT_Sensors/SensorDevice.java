package Java_IO.Networking.IoT_Sensors;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/*
 * Simulates an IoT temperature/humidity sensor.
 * Sends periodic updates to the dashboard server.
 */
public class SensorDevice {
    public static void main(String[] args) {
        String sensorId = args.length > 0 ? args[0] : "Sensor-001";
        Random random = new Random();

        try (
                Socket socket = new Socket("localhost", 7000);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            while (true) {
                int temp = 20 + random.nextInt(15);
                int humidity = 40 + random.nextInt(30);
                String data = sensorId + " → Temp: " + temp + "°C, Humidity: " + humidity + "%";

                out.println(data);
                TimeUnit.SECONDS.sleep(2); // Simulate delay
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
