package Deep_Threading.Thread_Pools.Q12;

import java.util.concurrent.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class ScheduledThreadPoolExample
{
    public static void main(String[] args)
    {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Run after 2 seconds
        scheduler.schedule(() -> {
            System.out.println(" Scheduled task (delayed) and is executed at " + LocalDateTime.now().format(formatter) );


        } , 2 , TimeUnit.SECONDS);

        //  Run every 3 seconds (repeated)
        scheduler.scheduleAtFixedRate(() -> {

            System.out.println(" Scheduled repeating task executed at : " + LocalDateTime.now().format(formatter));

        } , 1 , 3 , TimeUnit.SECONDS);


        // Let it run for 10 seconds before shutdown

        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException ignored) {

        }
        scheduler.shutdown();


    }



}
