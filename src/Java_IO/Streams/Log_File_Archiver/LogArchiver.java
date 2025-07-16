package Java_IO.Streams.Log_File_Archiver;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * LogArchiver reads a system log file and filters out ERROR lines,
 * then writes them to a separate archive file with a timestamp.
 */
public class LogArchiver
{
    private File sourceLogFile;
    private File errorArchiveFile;

    public LogArchiver(String logFilePath) {
        this.sourceLogFile = new File(logFilePath);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        this.errorArchiveFile = new File("error_log_" + timestamp + ".txt");
    }

    public void archiveErrors() throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(sourceLogFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(errorArchiveFile))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("ERROR")) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
        System.out.println("Archived ERROR logs to: " + errorArchiveFile.getAbsolutePath());
    }



}
