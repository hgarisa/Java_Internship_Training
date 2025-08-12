package Java_Threads.Thread_Synchronization.Read_Mostly_Catalog;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Catalog
{
    private final Map<String, Double> map = new ConcurrentHashMap<>();
    private final ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock r = rw.readLock();
    private final ReentrantReadWriteLock.WriteLock w = rw.writeLock();

    public void put(String sku, double price) {
        w.lock();
        try {
            map.put(sku, price);
        } finally {
            w.unlock();
        }
    }

    public double get(String sku) {
        r.lock();
        try {
            return map.getOrDefault(sku, Double.NaN);
        } finally {
            r.unlock();
        }
    }
    /**
     * Lock downgrade pattern:
     * 1) Acquire writeLock (exclusive) to modify safely.
     * 2) BEFORE releasing writeLock, acquire readLock.
     * 3) Release writeLock -> we still hold readLock to read back/validate.
     */
    public void adjustWithDowngrade(String sku, double delta) {
        w.lock();
        try {
            double cur = map.getOrDefault(sku, 0.0);
            map.put(sku, cur + delta);

            // DOWNGRADE: acquire read before releasing write
            r.lock();
        } finally {
            w.unlock();
        }
        try { // now we only hold read lock
            double after = map.getOrDefault(sku, 0.0);
            System.out.printf("[%s] adjusted %s by %.2f -> %.2f%n",
                    Thread.currentThread().getName(), sku, delta, after);
        } finally {
            r.unlock();
        }
    }

}


