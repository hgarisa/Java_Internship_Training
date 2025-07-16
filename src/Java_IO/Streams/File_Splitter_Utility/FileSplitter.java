package Java_IO.Streams.File_Splitter_Utility;

import java.io.*;

/*
 * Splits a file into smaller parts of fixed size in KB.
 */
public class FileSplitter
{
    /*
     * Splits the input file into smaller parts.
     */
    public void splitFile(FileInfo fileInfo, int chunkSizeKB) throws IOException {
        File inputFile = fileInfo.getFile();
        byte[] buffer = new byte[chunkSizeKB * 1024]; // buffer per chunk
        int partNumber = 1;

        try (FileInputStream fis = new FileInputStream(inputFile)) {
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) > 0) {
                String partFileName = inputFile.getName() + ".part" + partNumber++;
                File outputFile = new File(fileInfo.getParentDirectory(), partFileName);

                try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                    fos.write(buffer, 0, bytesRead);
                    System.out.println(" Created: " + outputFile.getAbsolutePath());
                }
            }
        }
    }


}
