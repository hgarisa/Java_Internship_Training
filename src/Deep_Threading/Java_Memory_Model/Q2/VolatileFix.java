package Deep_Threading.Java_Memory_Model.Q2;

public class VolatileFix {
    static volatile int a = 0, b = 0; // only a,b need to be volatile
    static int x = 0, y = 0;

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10000; i++) {
            a = b = x = y = 0;
            Thread t1 = new Thread(() -> { a = 1; x = b; });
            Thread t2 = new Thread(() -> { b = 1; y = a; });
            t1.start(); t2.start(); t1.join(); t2.join();
            if (x == 0 && y == 0) { System.out.println("Saw (0,0) — unexpected"); return; }

        }

        System.out.println("No (0,0) seen");
    }
}

