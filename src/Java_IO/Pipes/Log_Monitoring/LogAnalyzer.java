package Java_IO.Pipes.Log_Monitoring;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
  Analyzes log entries coming through the pipe and counts occurrences.
 */
public class LogAnalyzer extends Thread
{
    private final InputStream inputStream;

    public LogAnalyzer(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    @Override
    public void run() {
        int errorCount = 0, warnCount = 0, infoCount = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ERROR")) errorCount++;
                else if (line.startsWith("WARN")) warnCount++;
                else if (line.startsWith("INFO")) infoCount++;
            }

            System.out.println("\n--- Log Summary ---");
            System.out.println("INFO: " + infoCount);
            System.out.println("WARN: " + warnCount);
            System.out.println("ERROR: " + errorCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
