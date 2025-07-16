package Java_Reflection.Plugin_Loader;

import java.time.LocalDateTime;

public class TimeCommand implements Command {
    public void run() {
        System.out.println("Current time is: " + LocalDateTime.now());
    }
}

