package Deep_Threading.Java_Memory_Model.Q1;

public class ReorderingDemo {
    static int a = 0, b = 0;
    static int x = 0, y = 0;

    public static void main(String[] args) throws Exception {
        int tries = 1_000_000;
        for (int i = 0; i < tries; i++) {
            a = b = x = y = 0;
            Thread t1 = new Thread(() -> { a = 1; x = b; });
            Thread t2 = new Thread(() -> { b = 1; y = a; });
            t1.start(); t2.start(); t1.join(); t2.join();
            if (x == 0 && y == 0) { System.out.println("(0,0) at try " + i); break; }
        }
    }
}
