package Deep_Threading.Ring_Buffer.Q4;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

/** Lock-free MPMC ring buffer using per-slot sequence numbers. */
public final class MpmcRingBuffer<T> {
    private final AtomicReferenceArray<T> buffer;
    private final AtomicLongArray seq; // per-slot sequence numbers
    private final int capacity;
    private final int mask;

    private final AtomicLong head = new AtomicLong(0); // consumer claim counter
    private final AtomicLong tail = new AtomicLong(0); // producer claim counter

    public MpmcRingBuffer(int capacity) {
        if (capacity <= 0 || (capacity & (capacity - 1)) != 0) {
            throw new IllegalArgumentException("capacity must be a power of two and > 0");
        }
        this.capacity = capacity;
        this.mask = capacity - 1;
        this.buffer = new AtomicReferenceArray<>(capacity);
        this.seq = new AtomicLongArray(capacity);
        // Initialize each slot's sequence, so it's free for its first use (t == i)
        for (int i = 0; i < capacity; i++) {
            seq.set(i, i);
        }
    }

    /** Non-blocking enqueue; returns false if appears full. */
    public boolean offer(T value) {
        if (value == null) throw new NullPointerException("null elements not supported");
        long t;
        int idx;
        long s;
        for (;;) {
            t = tail.get(); // t = 0
            idx = (int)(t & mask);  // idx = 0
            s = seq.get(idx); // s = 0
            long dif = s - t; // dif = 0
            if (dif == 0) {
                if (tail.compareAndSet(t, t + 1))  // moves the tail foward
                {
                    break; // reserved this sequence
                }
            } else if (dif < 0) {
                // slot not yet freed for this cycle -> appears full
                return false;
            } else {
                // another producer working; spin a bit
                Thread.onSpinWait();
            }
        }
        buffer.set(idx, value);   // idx = 0  // release write of payload // (0,0)
       // System.out.println("Value is : " + value);
        seq.lazySet(idx, t + 1);    // seq[0] = 1 // seq[idx] = t + 1  // publish ready (release)
        return true;
    }

    /** Non-blocking dequeue; returns null if appears empty. */
    public T poll() {
        long h;
        int idx;
        long s;
        for (;;) {
            h = head.get();
            idx = (int)(h & mask);
            s = seq.get(idx);
            long dif = s - (h + 1);
            if (dif == 0) {
                if (head.compareAndSet(h, h + 1))  // moves the head forward
                {
                    break; // reserved to consume
                }
            } else if (dif < 0)
            {
                // not yet published -> appears empty
                return null;
            } else {
                // another consumer working; spin a bit
                Thread.onSpinWait();
            }
        }
        T v = buffer.get(idx);    // idx = 0             // acquire read
        buffer.lazySet(idx, null);       //        // help GC (release)
        seq.lazySet(idx, h + capacity);        // mark free for next producer cycle (release)
        return v;
    }
}
