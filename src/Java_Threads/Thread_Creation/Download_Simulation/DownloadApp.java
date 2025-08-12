package Java_Threads.Thread_Creation.Download_Simulation;

public class DownloadApp
{

    public static void main(String[] args) {
        // Create Runnable tasks
        Runnable downloadTask1 = new FileDownloader("file1.txt");
        Runnable downloadTask2 = new FileDownloader("file2.txt");

        // Start threads from Runnable
        Thread t1 = new Thread(downloadTask1);
        Thread t2 = new Thread(downloadTask2);

        t1.start();
        t2.start();
    }

}
