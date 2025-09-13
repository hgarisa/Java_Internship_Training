package Deep_Threading.Ring_Buffer.Q2;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class RingBufferSpsc<T>
{
    private final AtomicReferenceArray<T> buffer;
    private final int capacity;
    private final int mask; // for fast modulo when capacity is power of two

    // head = next index to read; tail = next index to write
    private final AtomicLong head = new AtomicLong(0);
    private final AtomicLong tail = new AtomicLong(0);

    public RingBufferSpsc(int capacity) {
        if (capacity <= 0 || (capacity & (capacity - 1)) != 0)
            throw new IllegalArgumentException("capacity must be a power of two and > 0");
        this.capacity = capacity;
        this.mask = capacity - 1;
        this.buffer = new AtomicReferenceArray<>(capacity);
    }

    // Non-blocking offer; returns false if full
    public boolean offer(T value) {
        long t = tail.get();
        long h = head.get(); // consumer's read index
        if ((t - h) == capacity) {
            // full
            return false;
        }
        int idx = (int)(t & mask);
        // Should be empty here; in SPSC we can just set
        buffer.set(idx, value); // set() acts as release for this slot
        tail.lazySet(t + 1);    // publish new tail (lazySet is cheaper)
        return true;
    }

    // Non-blocking poll; returns null if empty
    public T poll() {
        long h = head.get();
        long t = tail.get(); // producer's write index
        if (h == t) {
            // empty
            return null;
        }
        int idx = (int)(h & mask);
        T v = buffer.get(idx);  // get() acts as acquire
        buffer.lazySet(idx, null); // help GC; optional for pure pointer-math style
        head.lazySet(h + 1);
        return v;
    }

}
