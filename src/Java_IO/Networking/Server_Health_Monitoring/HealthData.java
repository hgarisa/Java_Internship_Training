package Java_IO.Networking.Server_Health_Monitoring;
import java.io.Serializable;

/*
  Represents health metrics for a simulated remote server.
 */

/*
*
* Scenario:
You are building a system admin tool to
* monitor the health of multiple servers (like disk space, memory usage, uptime).
* Each remote server sends periodic health stats to a central monitoring dashboard server
* via a socket connection.
*
*
*
*
* */

public class HealthData implements Serializable {
    private String serverName;
    private double cpuUsage;     // %
    private double memoryUsage;  // %
    private long uptime;         // seconds

    public HealthData(String serverName, double cpuUsage, double memoryUsage, long uptime) {
        this.serverName = serverName;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.uptime = uptime;
    }

    @Override
    public String toString() {
        return String.format("Server: %s | CPU: %.2f%% | Memory: %.2f%% | Uptime: %d sec",
                serverName, cpuUsage, memoryUsage, uptime);
    }
}

