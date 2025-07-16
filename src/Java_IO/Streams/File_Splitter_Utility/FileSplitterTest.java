package Java_IO.Streams.File_Splitter_Utility;
import java.io.IOException;

/*

Scenario
You're building a utility that:

Splits a large file (e.g., 10MB) into smaller chunks (e.g., 100KB each)

This is useful when:

Uploading files to services with size limits

Sending large files over email

Performing incremental backups

 */


/*
Entry point for testing the file splitter utility.
 */
public class FileSplitterTest
{
    public static void main(String[] args) throws IOException {
        String fileToSplit = "big_sample.txt";
        int chunkSizeKB = 100; // Split into 100KB parts

        FileInfo fileInfo = new FileInfo(fileToSplit);
        FileSplitter splitter = new FileSplitter();
        splitter.splitFile(fileInfo, chunkSizeKB);
    }


}
