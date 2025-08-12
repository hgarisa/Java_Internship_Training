package Java_Threads.Easy_Level_Questions.volatileExamples.Q2;

public class VolatileCounterBroken
{
    static volatile int count = 0;

    public static void main(String[] args) throws Exception {
        Runnable task = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                count++; // read, add, write (3 steps) -> race
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        long start = System.currentTimeMillis();
        t1.start(); t2.start();
        t1.join();  t2.join();
        long end = System.currentTimeMillis();

        System.out.println("Expected: 2000000, Actual: " + count + " (in " + (end-start) + " ms)");
    }

}
