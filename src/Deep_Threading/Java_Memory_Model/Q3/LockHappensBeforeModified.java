package Deep_Threading.Java_Memory_Model.Q3;

public class LockHappensBeforeModified
{
    static final Object LOCK = new Object();
    static int shared;

    public static void main(String[] args) throws Exception {
        Thread producer = new Thread(() -> {
            synchronized (LOCK) { shared = 42; }
        });
        Thread consumer = new Thread(() -> {
            synchronized (LOCK) { System.out.println(shared); } // must see 42
        });
        producer.start(); producer.join();
        consumer.start(); consumer.join();
    }


}
