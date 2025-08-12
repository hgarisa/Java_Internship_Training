package Java_Threads.Thread_Synchronization.Warehouse_Inventory;

import java.util.HashMap;
import java.util.Map;

public class Warehouse
{
    private final Map<String, Integer> stock = new HashMap<>();

    public synchronized void addSku(String sku, int initial) {
        stock.put(sku, initial);
    }

    public synchronized void restock(String sku, int delta) {
        stock.merge(sku, delta, Integer::sum);
        System.out.printf("[restock] %s +%d new=%d%n", sku, delta, stock.getOrDefault(sku, 0));
        notifyAll(); // wake all waiting reservations
    }

    /**
     * Blocks until enough units exist, then decrements stock.
     * Uses a WHILE loop (not IF) to guard against spurious wakeups.
     */
    public synchronized void reserveBlocking(String sku, int qty) throws InterruptedException {
        while (stock.getOrDefault(sku, 0) < qty) {
            System.out.printf("[%s] waiting: need=%d have=%d%n",
                    Thread.currentThread().getName(), qty, stock.getOrDefault(sku, 0));
            wait(); // releases monitor; re-acquires before returning
        }
        stock.put(sku, stock.get(sku) - qty);
    }

    @Override public synchronized String toString() { return stock.toString(); }
}

