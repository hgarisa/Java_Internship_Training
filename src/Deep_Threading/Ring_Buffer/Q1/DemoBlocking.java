package Deep_Threading.Ring_Buffer.Q1;

public class DemoBlocking {
    public static void main(String[] args) {
        RingBufferBlocking<Integer> rb = new RingBufferBlocking<>(5);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    rb.put(i);
                    System.out.println("Produced " + i);
                }
            } catch (InterruptedException ignored) {}
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    Integer v = rb.take();
                    System.out.println("  Consumed " + v);
                }
            } catch (InterruptedException ignored) {}
        });

        producer.start();
        consumer.start();
    }
}
