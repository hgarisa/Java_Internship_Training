import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Project 3: Latency Benchmark & Throttling Simulator for an HFT Order Gateway.
 *
 * HFT concept:
 * - Trading systems often send orders to an "exchange gateway" component.
 * - They need to measure how long it takes from sending the order until an acknowledgement returns.
 * - They must also respect rate limits (throttling) to avoid overloading the exchange.
 *
 * This example:
 * - Simulates sending a batch of orders to an external system with random network/processing delay.
 * - Measures latencies for each order and prints simple statistics (min, max, average).
 * - Demonstrates a simple fixed-rate throttling: we limit how many orders per second we send.
 */


public class LatencyBenchmarkGateway
{

    /**
     * Represents a very basic order (just enough for the simulation).
     */
    static class OrderRequest {
        final String symbol;
        final double price;
        final long quantity;

        OrderRequest(String symbol, double price, long quantity) {
            this.symbol = symbol;
            this.price = price;
            this.quantity = quantity;
        }
    }

    /**
     * Result of sending an order: includes how long it took.
     */
    static class OrderResult {
        final OrderRequest order;
        final Duration latency;
        final boolean success;

        OrderResult(OrderRequest order, Duration latency, boolean success) {
            this.order = order;
            this.latency = latency;
            this.success = success;
        }
    }

    /**
     * Simulates an "exchange gateway" that handles an order with some artificial delay.
     * In a real HFT system, this would talk to a real exchange using FIX, binary protocols, etc.
     */
    static class ExchangeGatewaySimulator {
        private final Random random = new Random();

        public OrderResult sendOrder(OrderRequest order) {
            Instant start = Instant.now();

            // Simulate variable network and matching engine delay (1ms to 50ms)
            int processingMillis = 1 + random.nextInt(50);
            try {
                Thread.sleep(processingMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new OrderResult(order, Duration.between(start, Instant.now()), false);
            }

            // Simulate occasional rejected orders (e.g. risk checks fail)
            boolean success = random.nextDouble() > 0.05; // 95% success rate

            Instant end = Instant.now();
            return new OrderResult(order, Duration.between(start, end), success);
        }
    }

    /**
     * Simple latency stats calculator.
     */
    static class LatencyStats {
        private final List<Duration> latencies = new ArrayList<>();
        private int failures = 0;

        public synchronized void record(OrderResult result) {
            if (result.success) {
                latencies.add(result.latency);
            } else {
                failures++;
            }
        }

        public void printSummary() {
            if (latencies.isEmpty()) {
                System.out.println("No successful orders.");
                return;
            }

            Duration min = latencies.stream().min(Duration::compareTo).orElseThrow();
            Duration max = latencies.stream().max(Duration::compareTo).orElseThrow();
            double avgMillis = latencies.stream()
                    .mapToLong(Duration::toMillis)
                    .average()
                    .orElse(0.0);

            System.out.println("===== Latency Stats =====");
            System.out.println("Total orders sent      : " + (latencies.size() + failures));
            System.out.println("Successful orders      : " + latencies.size());
            System.out.println("Failed orders          : " + failures);
            System.out.println("Min latency (ms)       : " + min.toMillis());
            System.out.println("Max latency (ms)       : " + max.toMillis());
            System.out.println("Avg latency (ms)       : " + avgMillis);
            System.out.println("=========================");
        }
    }

    public static void main(String[] args) {
        int totalOrders = 200;   // how many orders to send in this test
        int maxOrdersPerSecond = 50; // throttling limit

        ExchangeGatewaySimulator gateway = new ExchangeGatewaySimulator();
        LatencyStats stats = new LatencyStats();

        // Thread pool to simulate multiple client threads sending orders in parallel.
        ExecutorService executor = Executors.newFixedThreadPool(8);

        List<Future<OrderResult>> futures = new ArrayList<>();

        Instant batchStart = Instant.now();

        for (int i = 0; i < totalOrders; i++) {
            // Simple throttling: if we sent 'maxOrdersPerSecond' orders in less than 1 second,
            // pause until 1 second has passed since batchStart and then reset counters.
            if ((i > 0) && (i % maxOrdersPerSecond == 0)) {
                // We finished one "window" of orders; enforce that each window takes at least 1 second
                Instant now = Instant.now();
                long millisSinceBatchStart = Duration.between(batchStart, now).toMillis();
                long oneSecond = 1000L;

                if (millisSinceBatchStart < oneSecond) {
                    long sleepMillis = oneSecond - millisSinceBatchStart;
                    try {
                        Thread.sleep(sleepMillis);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }

                // Start a new time window
                batchStart = Instant.now();
            }

            // Create a new order with some changing price and size
            OrderRequest order = new OrderRequest(
                    "AAPL",
                    100.0 + (i % 10),  // vary price slightly
                    10 + (i % 5)       // vary quantity slightly
            );

            // Submit asynchronous task to send order and measure latency
            Callable<OrderResult> task = () -> gateway.sendOrder(order);
            futures.add(executor.submit(task));
        }

        // Collect all results
        for (Future<OrderResult> future : futures) {
            try {
                OrderResult result = future.get();
                stats.record(result);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Interrupted while waiting for result.");
            } catch (ExecutionException e) {
                System.err.println("Error while sending order: " + e.getCause());
            }
        }

        // Shut down executor
        executor.shutdown();

        // Print latency summary at the end
        stats.printSummary();

        // Ideas to extend this project:
        // - Track p50/p90/p99 latency percentiles.
        // - Group stats per symbol if you simulate multiple instruments.
        // - Add a simple risk-check layer that can reject some orders before they reach the gateway.
        // - Persist latency metrics to a file or time-series database and visualize them.
    }




}
