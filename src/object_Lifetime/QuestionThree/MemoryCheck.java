package object_Lifetime.QuestionThree;

public class MemoryCheck {

    public static void main(String[] args) { // (6)
        int blobsRequired, blobSize;

        // -------- Parse Command-Line Arguments --------
        try {
            blobsRequired = Integer.parseInt(args[0]);  // Number of blobs to create
            blobSize = Integer.parseInt(args[1]);       // Size of each blob (int array length)
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Usage: MemoryCheck <number of blobs> <blob size>");
            return; // Exit if arguments are missing
        }

        // -------- Access Runtime Environment --------
        Runtime environment = Runtime.getRuntime(); // (7)
        // `Runtime.getRuntime()` returns the JVM's runtime instance

        // -------- Display Memory Info BEFORE Blob Creation --------
        System.out.println("Total memory: " + environment.totalMemory()); // (8)
        System.out.println("Free memory before blob creation: " +
                environment.freeMemory()); // (9)

        // -------- Create Blobs Dynamically --------
        for (int i = 0; i < blobsRequired; ++i) { // (10)
            new Blob(blobSize);
            // Each blob allocates a large array; reference is not stored → immediately eligible for GC
            // finalize() logs "Hello" on creation and "Bye" on GC
        }

        // -------- Display Memory Info AFTER Blob Creation --------
        System.out.println("Free memory after blob creation: " +
                environment.freeMemory()); // (11)
        // At this point, JVM memory is consumed by unreferenced Blob objects

        // -------- Suggest JVM to Run Garbage Collection --------
        System.gc(); // (12)
        // Requests garbage collection (but JVM may delay or ignore this)

        // -------- Display Memory Info AFTER GC Request --------
        System.out.println("Free memory after requesting GC: " +
                environment.freeMemory()); // (13)
        // If GC ran, you may see an increase in free memory and finalize() "Bye" messages

        // -------- Show Remaining Population (Blobs still alive) --------
        System.out.println(BasicBlob.population + " blobs alive"); // (14)
        // Blob population is tracked via a static counter in BasicBlob
    }
}
