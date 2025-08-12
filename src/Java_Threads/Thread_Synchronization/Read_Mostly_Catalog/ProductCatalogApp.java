package Java_Threads.Thread_Synchronization.Read_Mostly_Catalog;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Scenario:
 * - Many readers look up product prices; occasional writers update prices.
 * - Use ReadWriteLock to allow many concurrent reads but exclusive writes.
 * - Demonstrates lock DOWNGRADING: writeLock -> readLock without becoming fully unlocked.
 * Notes:
 * - No explicit Condition here: focus is on read/write synchronization with better concurrency.
 */
public class ProductCatalogApp
{
    public static void main(String[] args) throws InterruptedException {
        Catalog catalog = new Catalog();
        catalog.put("SKU-1", 100.0);
        catalog.put("SKU-2", 130.0);

        ExecutorService pool = Executors.newFixedThreadPool(6);

        // Reader tasks
        for (int i = 0; i < 4; i++) {
            pool.submit(() -> {
                for (int k = 0; k < 50; k++) {
                    double p = catalog.get("SKU-1");
                    if (k % 20 == 0) System.out.printf("[%s] price SKU-1=%.2f%n", Thread.currentThread().getName(), p);
                }
            });
        }

        // Writer that adjusts prices, showing downgrade pattern
        pool.submit(() -> {
            for (int i = 0; i < 5; i++) {
                catalog.adjustWithDowngrade("SKU-1", +5.0);
                sleep(120);
            }
        });

        // Another writer (regular put)
        pool.submit(() -> {
            for (int i = 0; i < 3; i++) {
                catalog.put("SKU-2", 140 + i);
                sleep(200);
            }
        });

        pool.shutdown();
        pool.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println("Done. Final SKU-1=" + catalog.get("SKU-1") + ", SKU-2=" + catalog.get("SKU-2"));
    }

    static void sleep(long ms) { try { Thread.sleep(ms); } catch (InterruptedException ignored) {} }

}
