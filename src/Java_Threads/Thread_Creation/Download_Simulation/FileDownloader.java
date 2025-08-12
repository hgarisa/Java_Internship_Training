package Java_Threads.Thread_Creation.Download_Simulation;

// Runnable task: only knows "what to do", not "how it runs"
public class FileDownloader implements Runnable
{
    private String fileName;

    public FileDownloader(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public void run()
    {
        System.out.println(Thread.currentThread().getName() + " started downloading " + fileName);
        try {
            Thread.sleep(1000); // simulate time taken to download
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " finished downloading " + fileName);
    }


}

