package object_Lifetime.QuestionTwo;

import object_Lifetime.QuestionTwo.BasicBlob;

public class Blob extends BasicBlob {

    int[] size; // Represents "bloatedness" (simulated memory consumption)

    // Constructor initializes memory and prints a greeting
    Blob(int bloatedness) { // (4)
        size = new int[bloatedness]; // Allocate a big array to simulate a heavy object
        System.out.println(blobId + ": Hello"); // Log creation
    }

    // finalize() prints a goodbye message when collected
    @Override
    protected void finalize() throws Throwable { // (5)
        System.out.println(blobId + ": Bye"); // Log destruction
        super.finalize(); // Call base finalize() to adjust population
    }
}
