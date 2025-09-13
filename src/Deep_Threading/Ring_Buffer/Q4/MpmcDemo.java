package Deep_Threading.Ring_Buffer.Q4;

public class MpmcDemo {
    public static void main(String[] args) throws InterruptedException {
       // final int CAPACITY = 1024; // power of two
        final MpmcRingBuffer<Integer> q = new MpmcRingBuffer<>(8);

        // ---- Producer 1 (prints as "Thread 1") ----
        Thread producer1 = new Thread(() -> {
            try {
                for (int i = 0; i <= 20; i++) {
                    // spin until offered
                    while (!q.offer(i)) {
                        Thread.onSpinWait();
                    }
                    System.out.println("Thread 1 : Produced " + i);
                }
            } catch (Exception ignored) {}
        }, "producer-1");

        // ---- Consumer 1 (prints as "Thread 2") ----
        Thread consumer1 = new Thread(() -> {
            try {
                int consumed = 0;
                while (consumed < 20) {
                    Integer v = q.poll();
                    if (v == null) {
                        Thread.onSpinWait();
                        continue;
                    }
                    System.out.println("Thread 2 : Consumed " + v);
                    consumed++;
                }
            } catch (Exception ignored) {}
        }, "consumer-1");

        // ---- Producer 2 (your style; prints as "Thread 3") ----
        Thread producer2 = new Thread(() -> {
            try {
                for (int i = 0; i <= 20; i++) {
                    while (!q.offer(i)) {
                        Thread.onSpinWait(); // "blocks" (spins) if buffer full
                    }
                    System.out.println(" Thread 3 :  Produced " + i);
                }
            } catch (Exception ignored) {}
        }, "producer-2");

        // ---- Consumer 2 (your style; prints as "Thread 4") ----
        Thread consumer2 = new Thread(() -> {
            try {
                int consumed = 0;
                while (consumed < 20) {
                    Integer v = q.poll();
                    if (v == null) {
                        Thread.onSpinWait(); // "blocks" (spins) if buffer empty
                        continue;
                    }
                    System.out.println(" Thread 4 : Consumed " + v);
                    consumed++;
                }
            } catch (Exception ignored) {}
        }, "consumer-2");

        // Start all four threads
        producer1.start();
        consumer1.start();
        producer2.start();
        consumer2.start();

        // Join
        producer1.join();
        consumer1.join();
        producer2.join();
        consumer2.join();

        System.out.println("Done.");
    }
}
