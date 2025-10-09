package Deep_Threading.Java_Memory_Model.Q3;

public class NonVolatileReady
{
    static int shared = 0;
    static  boolean ready = false;
    public static void main(String[] args) throws Exception {
        Thread producer = new Thread(() -> {
            shared = 42;        // write
            ready = true;       // signal (non-volatile!)
        });
        Thread consumer = new Thread(() -> {
            while (!ready) {
            }  // spin
            System.out.println(shared); // can print 0 (!)
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

    /*
    *
    * On most desktop CPUs (x86/AMD64),
    *  stores are not reordered with other stores (a “store→store” ordering).
    * So if the consumer sees ready == true,
    *  that implies the earlier store shared = 42 is already visible too.
    *  Result: you almost always print 42.
    *
    *
    * On some machines you’ll now see 0 sometimes.
    * (On weaker-memory CPUs like ARM, you’re more likely to see it even without the sleep.)
    *
    *
    * */
}
