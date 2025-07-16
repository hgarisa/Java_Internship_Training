package Java_IO.Pipes.File_Compression;
import java.io.*;
/*
  Main class that connects a file reader to a file compressor using piped streams.
 */
public class FileCompressionApp {
    public static void main(String[] args) throws IOException {
        File input = new File("big_sample.txt");
        File output = new File("compressed_output.gz");

        PipedOutputStream pipedOut = new PipedOutputStream();
        PipedInputStream pipedIn = new PipedInputStream(pipedOut);

        FileReaderThread reader = new FileReaderThread(input, pipedOut);
        FileCompressorThread compressor = new FileCompressorThread(pipedIn, output);

        reader.start();
        compressor.start();
    }
}

