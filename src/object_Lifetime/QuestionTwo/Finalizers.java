package object_Lifetime.QuestionTwo;

public class Finalizers {

    public static void main(String[] args) { // (6)
        int blobsRequired, blobSize;

        try {
            // Parse user input: number of blobs to create and size of each blob
            blobsRequired = Integer.parseInt(args[0]);
            blobSize = Integer.parseInt(args[1]);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Usage: Finalizers <number of blobs> <blob size>");
            return;
        }

        // Create multiple blob objects (unreferenced, eligible for GC)
        for (int i = 0; i < blobsRequired; ++i) { // (7)
            new Blob(blobSize); // No variable holds the reference → eligible for GC
        }

        // Output the number of blobs still alive (based on static counter)
        System.out.println(BasicBlob.population + " blobs alive"); // (8)
    }
}
