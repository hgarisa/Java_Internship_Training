package Java_IO.Networking.Server_Health_Monitoring;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
  Simulates a remote server node that sends health metrics periodically.
 */
public class ServerNode {
    public static void main(String[] args) throws Exception {
        String serverName = "Server-1"; // Change this for different nodes

        while (true) {
            try (Socket socket = new Socket("localhost", 5000);
                 ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {

                HealthData data = HealthDataSimulator.generate(serverName);
                oos.writeObject(data);
                Thread.sleep(5000); // send every 5 seconds
            }
        }
    }
}

