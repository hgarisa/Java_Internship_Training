package Java_IO.Pipes.Log_Monitoring;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/*
  Main class that connects a log generator to a log analyzer using a pipe.
 */

public class LogMonitorApp
{
    public static void main(String[] args) throws Exception {
        PipedOutputStream pipedOut = new PipedOutputStream();
        PipedInputStream pipedIn = new PipedInputStream(pipedOut);

        LogGenerator generator = new LogGenerator(pipedOut);
        LogAnalyzer analyzer = new LogAnalyzer(pipedIn);

        generator.start();
        analyzer.start();
    }


}
