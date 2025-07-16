package Java_IO.Streams.File_Splitter_Utility;

import java.io.File;
public class FileInfo
{

    private File file;

    public FileInfo(String filePath) {
        this.file = new File(filePath);
    }

    public File getFile() {
        return file;
    }

    public long getSizeInBytes() {
        return file.length();
    }

    public String getName() {
        return file.getName();
    }

    public String getParentDirectory() {
        return file.getParent();
    }



}
