package Java_Threads.Thread_Synchronization.Warehouse_Inventory;


/**
 * Scenario:
 * - Multiple order threads try to RESERVE units of a SKU.
 * - If stock is insufficient, an order thread WAITS.
 * - A restock thread adds units and NOTIFIES all waiting orders.

 * Why 'synchronized':
 * - Demonstrates intrinsic locking + wait()/notifyAll() without explicit Lock/Condition,
 *   which you still see in older codebases.
 */
public class WarehouseInventoryApp
{
    public static void main(String[] args) throws InterruptedException
    {

        Warehouse wh = new Warehouse();
        wh.addSku("LAPTOP-15", 2);

        // Order threads (might need to wait for stock)
        Thread o1 = new Thread(() -> reserve(wh, "LAPTOP-15", 2), "order-1");
        Thread o2 = new Thread(() -> reserve(wh, "LAPTOP-15", 1), "order-2");

        // Restock thread (wakes blocked orders)
        Thread restock = new Thread(() -> {
            sleep(800);
            wh.restock("LAPTOP-15", 3);
        }, "restock");

        o1.start(); o2.start(); restock.start();
        o1.join(); o2.join(); restock.join();
        System.out.println("Final stock: " + wh);
    }

    static void reserve(Warehouse wh, String sku, int qty) {
        try {
            wh.reserveBlocking(sku, qty);
            System.out.printf("[%s] reserved %d of %s%n", Thread.currentThread().getName(), qty, sku);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

     static void sleep(long ms) { try { Thread.sleep(ms); } catch (InterruptedException ignored) {} }
}

