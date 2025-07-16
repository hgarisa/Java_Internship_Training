package Java_IO.Networking.Server_Health_Monitoring;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 Central monitoring dashboard that collects health data from multiple servers.
 */
public class MonitoringDashboard {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Monitoring Dashboard listening on port 5000...");

        while (true) {
            Socket client = serverSocket.accept();

            new Thread(() -> {
                try (ObjectInputStream ois = new ObjectInputStream(client.getInputStream())) {
                    HealthData data = (HealthData) ois.readObject();
                    System.out.println("Received → " + data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


}

