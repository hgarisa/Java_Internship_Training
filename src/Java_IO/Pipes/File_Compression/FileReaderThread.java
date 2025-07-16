package Java_IO.Pipes.File_Compression;
import java.io.*;
/*
  Reads raw bytes from a file and writes them to a piped output stream.
 */
public class FileReaderThread extends Thread {
    private final File sourceFile;
    private final OutputStream outputStream;

    public FileReaderThread(File sourceFile, OutputStream outputStream) {
        this.sourceFile = sourceFile;
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        try (FileInputStream fis = new FileInputStream(sourceFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close(); // Important: signal EOF to the receiver
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

