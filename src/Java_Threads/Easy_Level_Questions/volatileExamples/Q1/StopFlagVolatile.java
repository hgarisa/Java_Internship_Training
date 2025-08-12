package Java_Threads.Easy_Level_Questions.volatileExamples.Q1;

public class StopFlagVolatile
{
    static volatile boolean running = true;  // <-- key change
    static long sink = 0;

    public static void main(String[] args) throws Exception {
        Thread worker = new Thread(() -> {
            long local = 0;
            while (running) {
                local += 1;
                if ((local & 0xFFFF_FFFFL) == 0) {
                    sink = local;
                    // Thread.onSpinWait(); // optional
                }
            }
            System.out.println("Worker saw running=false and stopped. sink=" + sink);
        }, "worker");

        worker.start();
        Thread.sleep(1000);
        System.out.println("Main: setting running=false");
        running = false;                // guaranteed visible
        worker.join(3000);
        System.out.println("Alive after join? " + worker.isAlive());
    }
}
