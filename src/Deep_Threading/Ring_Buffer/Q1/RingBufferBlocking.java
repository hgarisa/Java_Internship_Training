package Deep_Threading.Ring_Buffer.Q1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RingBufferBlocking<T> {
    private final Object[] buffer;
    private final int capacity;

    private int readPos = 0;   // next index to read
    private int writePos = 0;  // next index to write
    private int size = 0;      // number of elements currently in buffer

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull  = lock.newCondition();

    public RingBufferBlocking(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");
        this.capacity = capacity;
        this.buffer = new Object[capacity];
    }

    // Puts an element, waiting if the buffer is full
    public void put(T value) throws InterruptedException {
        lock.lock();
        try {
            while (size == capacity) {
                notFull.await();
            }
            buffer[writePos] = value;
            writePos = (writePos + 1) % capacity;
            size++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    // Takes an element, waiting if the buffer is empty
    @SuppressWarnings("unchecked")
    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (size == 0) {
                notEmpty.await();
            }
            T value = (T) buffer[readPos];
            buffer[readPos] = null; // help GC
            readPos = (readPos + 1) % capacity;
            size--;
            notFull.signal();
            return value;
        } finally {
            lock.unlock();
        }
    }

//    public int size() {
//        lock.lock();
//        try { return size; }
//        finally { lock.unlock(); }
//    }
}
