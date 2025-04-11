package object_Lifetime.QuestionOne;

public class HeavyItem {

    int[] itemBody;          // Simulates a "heavy" object by allocating a large array
    String itemID;           // Identifier to track the object
    HeavyItem nextItem;      // Reference to the next item (used in linked-list style)

    // Constructor creates a large array and assigns ID and link
    HeavyItem(String ID, HeavyItem itemRef) { // (2)
        itemBody = new int[100000];           // Large memory allocation
        itemID = ID;                          // Identifier
        nextItem = itemRef;                   // Link to the next item in the list
    }

    // finalize() is called by JVM before garbage collection
    @Override
    protected void finalize() throws Throwable { // (3)
        System.out.println(itemID + ": recycled."); // Message on object collection
        super.finalize(); // Always call super.finalize()
    }
}
