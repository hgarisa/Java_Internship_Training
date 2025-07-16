package Java_IO.Pipes.Log_Monitoring;

import java.io.OutputStream;
import java.io.PrintWriter;

/*
  Simulates a log-generating system that writes log entries to a pipe.
 */

public class LogGenerator extends Thread
{
    private final OutputStream outputStream;

    public LogGenerator(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        try (PrintWriter writer = new PrintWriter(outputStream)) {
            writer.println("INFO - Server started successfully");
            writer.println("ERROR - Database connection failed");
            writer.println("WARN - Disk space is running low");
            writer.println("INFO - Listening on port 8080");
            writer.println("ERROR - NullPointerException in UserService");
            writer.println("INFO - Backup completed successfully");
        }
    }


}
