package object_Lifetime.QuestionOne;

import object_Lifetime.QuestionOne.HeavyItem;

public class RecyclingBin {

    // Creates a single HeavyItem object (overwrites local references)
    public static HeavyItem createHeavyItem(String itemID) { // (4)
        HeavyItem itemA = new HeavyItem(itemID + " local item", null); // (5)
        // itemA here is eligible for GC after next line since reference is overwritten
        itemA = new HeavyItem(itemID, null); // (6)
        // Only the second object (ID: "itemID") survives and is returned
        System.out.println("Return from creating HeavyItem " + itemID);
        return itemA; // (7)
    }

    // Creates a 3-node linked list of HeavyItems
    public static HeavyItem createList(String listID) { // (8)
        HeavyItem item3 = new HeavyItem(listID + ": item3", null); // (9)
        HeavyItem item2 = new HeavyItem(listID + ": item2", item3); // (10)
        HeavyItem item1 = new HeavyItem(listID + ": item1", item2); // (11)
        // Only item1 is returned; item1 → item2 → item3 chain keeps all alive
        System.out.println("Return from creating list " + listID);
        return item1; // (12)
    }

    public static void main(String[] args) { // (13)

        // 1. Create list "X", but it gets immediately overwritten in (15)
        HeavyItem list = createList("X"); // (14)
        // At this point, listX (item1 → item2 → item3) becomes unreachable

        // 2. Create list "Y", which replaces list "X" reference
        list = createList("Y"); // (15)
        // Only listY remains referenced, listX is now garbage

        // 3. Create item "One", store it in itemOne
        HeavyItem itemOne = createHeavyItem("One"); // (16)

        // 4. Create item "Two", store it in itemTwo
        HeavyItem itemTwo = createHeavyItem("Two"); // (17)

        // 5. Set itemOne to null — now "One" object is unreachable
        itemOne = null; // (18)

        // 6. Create item "Three" without storing the reference
        // → object becomes unreachable immediately after creation
        createHeavyItem("Three"); // (19)

        // 7. Same for item "Four"
        createHeavyItem("Four"); // (20)

        // JVM is free to garbage collect unreferenced objects now
        System.out.println("Return from main().");
    }
   /* (14)	A list of 3 items "X: item1 → item2 → item3" is created.
            (15)	Reference is replaced with "Y: item1 → item2 → item3". X's list is now eligible for GC.
            (16)	Two HeavyItems are created: "One local item" (immediately lost) and "One" (stored).
            (17)	Similar process for "Two". Both "One" and "Two" are stored.
            (18)	itemOne is nullified → "One" becomes unreachable (GC candidate).
            (19)	"Three local item" and "Three" created, but both are lost — GC candidates.
            (20)	Same for "Four" and "Four local item".*/
}
