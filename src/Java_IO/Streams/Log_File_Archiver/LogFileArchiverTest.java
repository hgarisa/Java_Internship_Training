package Java_IO.Streams.Log_File_Archiver;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/*
Scenario:
Read a log file line by line, extract only lines with ERROR,
and save them into a new archive file with a timestamp in the filename.
 */
public class LogFileArchiverTest
{
    public static void main(String[] args) throws IOException {
        LogArchiver archiver = new LogArchiver("system.log");
        archiver.archiveErrors();
    }

}
