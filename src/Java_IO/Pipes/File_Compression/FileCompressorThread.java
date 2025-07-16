package Java_IO.Pipes.File_Compression;
import java.io.*;
import java.util.zip.GZIPOutputStream;

/*
  Reads raw bytes from piped input stream and writes compressed data to a .gz file.
 */
public class FileCompressorThread extends Thread {
    private final InputStream inputStream;
    private final File compressedFile;

    public FileCompressorThread(InputStream inputStream, File compressedFile) {
        this.inputStream = inputStream;
        this.compressedFile = compressedFile;
    }

    @Override
    public void run() {
        try (GZIPOutputStream gzipOut = new GZIPOutputStream(new FileOutputStream(compressedFile))) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                gzipOut.write(buffer, 0, bytesRead);
            }

            gzipOut.finish();
            System.out.println("Compression complete: " + compressedFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


