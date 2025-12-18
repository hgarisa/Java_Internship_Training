
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;


/**
 * Project 1: Market Data Feed using a Single Producer / Single Consumer (SPSC) Ring Buffer.
 *
 * HFT concept:
 * - In HFT, market data (price updates) must be passed very quickly from a "feed handler"
 *   to a "strategy engine".
 * - A common low-latency pattern is SPSC ring buffer: one producer thread, one consumer thread,
 *   no locks, just two moving indexes over a fixed-size array.
 *
 * This example:
 * - Producer thread: simulates incoming market data events.
 * - Consumer thread: reads the events, "processes" them, and counts how many events it handled.
 *
 * Run it as a normal Java application from IntelliJ.
 */

public class MarketDataFeedSPSC
{

    /**
     * Represents one market data tick for a given symbol.
     */
    static class MarketDataEvent {
        final String symbol;
        final double price;
        final long quantity;
        final long timestampNanos;

        MarketDataEvent(String symbol, double price, long quantity, long timestampNanos) {
            this.symbol = symbol;
            this.price = price;
            this.quantity = quantity;
            this.timestampNanos = timestampNanos;
        }

        @Override
        public String toString() {
            return "MarketDataEvent{" +
                    "symbol='" + symbol + '\'' +
                    ", price=" + price +
                    ", quantity=" + quantity +
                    ", timestampNanos=" + timestampNanos +
                    '}';
        }
    }

    /**
     * Very simple SPSC ring buffer implementation.
     * - One producer thread calls offer(...)
     * - One consumer thread calls poll(...)
     * - head: index where producer writes next
     * - tail: index where consumer reads next
     *
     * We use AtomicLong to make the indexes visible between threads without explicit locks.
     */
    static class RingBuffer {
        private final MarketDataEvent[] buffer;
        private final int capacity;

        // Index where the next element will be written by the producer
        private final AtomicLong head = new AtomicLong(0);
        // Index where the next element will be read by the consumer
        private final AtomicLong tail = new AtomicLong(0);

        RingBuffer(int capacity) {
            if (Integer.bitCount(capacity) != 1) {
                // Power of 2 capacity simplifies modulo operation, but it's not mandatory.
                throw new IllegalArgumentException("Capacity must be a power of 2 for this demo.");
            }
            this.capacity = capacity;
            this.buffer = new MarketDataEvent[capacity];
        }

        /**
         * Add an event into the ring buffer.
         *
         * @return true if added, false if buffer is full (producer should try again later).
         */
        public boolean offer(MarketDataEvent event) {
            long currentHead = head.get();
            long currentTail = tail.get();

            // If head - tail == capacity, buffer is full
            if (currentHead - currentTail >= capacity) {
                return false; // drop or back-pressure in a real system
            }

            int index = (int) (currentHead & (capacity - 1)); // same as currentHead % capacity for power-of-2

            buffer[index] = event;

            // Publish the new head index.
            // lazySet is cheaper than set and still gives visibility guarantees for this simple case.
            head.lazySet(currentHead + 1);
            return true;
        }

        /**
         * Read and remove the next event from the ring buffer.
         *
         * @return next event, or null if buffer is empty.
         */
        public MarketDataEvent poll() {
            long currentTail = tail.get();
            long currentHead = head.get();

            // If tail == head, buffer is empty
            if (currentTail >= currentHead) {
                return null;
            }

            int index = (int) (currentTail & (capacity - 1));
            MarketDataEvent event = buffer[index];
            buffer[index] = null; // avoid keeping references unnecessarily

            tail.lazySet(currentTail + 1);
            return event;
        }
    }

    /**
     * Simulates a market data source (exchange) pushing events into the ring buffer.
     */
    static class Producer implements Runnable {
        private final RingBuffer ringBuffer;
        private final String symbol;
        private final Random random = new Random();
        private volatile boolean running = true;

        Producer(RingBuffer ringBuffer, String symbol) {
            this.ringBuffer = ringBuffer;
            this.symbol = symbol;
        }

        public void stop() {
            running = false;
        }

        @Override
        public void run() {
            long sequence = 0;
            while (running) {
                // Simulate some random price/size
                double price = 100.0 + random.nextGaussian(); // around 100 with some noise
                long quantity = 1 + random.nextInt(1000);

                MarketDataEvent event = new MarketDataEvent(
                        symbol,
                        price,
                        quantity,
                        System.nanoTime()
                );

                // Try to put into ring buffer; if full, we "spin" briefly.
                while (running && !ringBuffer.offer(event)) {
                    // In ultra-low-latency code you might busy-spin; here we yield
                    Thread.onSpinWait();
                }

                sequence++;

                // Slow down producer a little so console output is readable.
                if (sequence % 1_000 == 0) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        }
    }

    /**
     * Simulates an HFT strategy that consumes market data and "processes" it.
     */
    static class Consumer implements Runnable {
        private final RingBuffer ringBuffer;
        private volatile boolean running = true;

        private long totalEvents;
        private double lastPrice;

        Consumer(RingBuffer ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        public void stop() {
            running = false;
        }

        public long getTotalEvents() {
            return totalEvents;
        }

        @Override
        public void run() {
            while (running) {
                MarketDataEvent event = ringBuffer.poll();
                if (event == null) {
                    // No events right now, back off a little
                    Thread.onSpinWait();
                    continue;
                }

                // "Process" event: here we just remember the last price and count events
                lastPrice = event.price;
                totalEvents++;

                // Occasionally print progress so we can see something in console
                if (totalEvents % 5_000 == 0) {
                    System.out.println(
                            "Consumer processed " + totalEvents +
                                    " events. Last price = " + lastPrice +
                                    " at " + Instant.now()
                    );
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 1. Create a small ring buffer with 1024 slots.
        RingBuffer ringBuffer = new RingBuffer(1024);

        // 2. Create producer and consumer.
        Producer producer = new Producer(ringBuffer, "AAPL");
        Consumer consumer = new Consumer(ringBuffer);

        // 3. Start both threads.
        Thread producerThread = new Thread(producer, "MarketDataProducer");
        Thread consumerThread = new Thread(consumer, "MarketDataConsumer");

        producerThread.start();
        consumerThread.start();

        // 4. Let the simulation run for a few seconds.
        Thread.sleep(5_000);

        // 5. Stop both threads gracefully.
        producer.stop();
        consumer.stop();

        // 6. Wait for threads to finish.
        producerThread.join();
        consumerThread.join();

        // 7. Print final stats.
        System.out.println("Simulation finished.");
        System.out.println("Total events processed = " + consumer.getTotalEvents());
    }

}

