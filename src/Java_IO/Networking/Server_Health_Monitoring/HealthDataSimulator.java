package Java_IO.Networking.Server_Health_Monitoring;
import java.util.Random;
/*
  Simulates system health metrics like CPU and memory usage.
 */
public class HealthDataSimulator {
    private static final Random random = new Random();

    public static HealthData generate(String serverName) {
        double cpu = 10 + random.nextDouble() * 80;      // 10% - 90%
        double memory = 30 + random.nextDouble() * 60;   // 30% - 90%
        long uptime = System.currentTimeMillis() / 1000;

        return new HealthData(serverName, cpu, memory, uptime);
    }
}
