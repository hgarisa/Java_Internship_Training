package Java_Threads.Easy_Level_Questions.volatileExamples.Q1;

public class StopFlagNoVolatile
{
    // NOT volatile on purpose
    static boolean running = true;
    // keep the JIT from optimizing the loop away
    static long sink = 0;
    public static void main(String[] args) throws Exception {
        Thread worker = new Thread(() -> {
            long local = 0;
            // a hot loop with minimal safepoints to amplify the problem
            while (running) {
                local += 1;
                if ((local & 0xFFFF_FFFFL) == 0) {
                    // very rare write to avoid too many safepoints
                    sink = local;
                    // optional: uncomment to reduce the chance of “accidentally” fixing it
                    // Thread.onSpinWait();
                }
            }
            System.out.println("Worker saw running=false and stopped. sink=" + sink);
        }, "worker");

        worker.start();
        Thread.sleep(1000);        // let it run
        System.out.println("Main: setting running=false");
        running = false;           // visibility problem here
        worker.join(3000);         // wait up to 3s
        System.out.println("Alive after join? " + worker.isAlive());
    }

}
