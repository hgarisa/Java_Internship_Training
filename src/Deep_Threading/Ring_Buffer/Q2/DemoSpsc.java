package Deep_Threading.Ring_Buffer.Q2;

public class DemoSpsc
{
    public static void main(String[] args) throws InterruptedException {
        RingBufferSpsc<Integer> rb = new RingBufferSpsc<>(8); // power of two

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                while (!rb.offer(i)) {
                    // spin briefly (or use Backoff) when full
                    Thread.onSpinWait();
                }
                System.out.println("Produced " + i);
            }
        });

        Thread consumer = new Thread(() -> {
            int received = 0;
            while (received < 20) {
                Integer v = rb.poll();
                if (v != null) {
                    System.out.println("  Consumed " + v);
                    received++;
                } else {
                    // spin briefly when empty
                    Thread.onSpinWait();
                }
            }
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
