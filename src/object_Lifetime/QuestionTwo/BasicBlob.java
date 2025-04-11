package object_Lifetime.QuestionTwo;

public class BasicBlob {

    static int idCounter;    // Shared counter for assigning unique blob IDs
    static int population;   // Tracks how many Blob objects are currently alive

    protected int blobId;    // Unique ID for each blob (assigned at construction)

    // Constructor increments counters and assigns ID
    BasicBlob() {
        blobId = idCounter++;  // Assign current ID and then increment counter
        ++population;          // Increase global blob population
    }

    // finalize() is called before the object is garbage collected
    @Override
    protected void finalize() throws Throwable { // (2)
        --population; // Decrease the blob count before the object is collected
        super.finalize();
    }
}
