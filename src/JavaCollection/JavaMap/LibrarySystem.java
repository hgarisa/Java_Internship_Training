package JavaCollection.JavaMap;

import java.util.*;
public class LibrarySystem
{

    public static void main(String[] args) {
        // 1. Create a Map to store ISBN -> LibraryBook

        Map<String, LibraryBook> libraryA = new HashMap<>();

        // 2. Insert books using .put()

        libraryA.put("ISBN001" , new LibraryBook("Java Basics" , "Alice" , 2020));
        libraryA.put("ISBN002", new LibraryBook("Data Structures", "Bob", 2018));
        libraryA.put("ISBN003", new LibraryBook("Algorithms", "Charlie", 2019));

        // 3. Insert with same key (replaces the old one)
        libraryA.put("ISBN002" , new LibraryBook("Advanced Data Structures " , "Bob" , 2022));

        // 4. Insert null key

        libraryA.put(null , new LibraryBook("Null Key Book" , "Unknown" , 2021));

        // 5. Insert null value

        libraryA.put("ISBN004" , null);

        // 6. Print all entries

        System.out.println("Library A:");

        for (String isbn : libraryA.keySet() )
        {
            System.out.println("ISBN : " +  isbn + " -> "  + libraryA.get(isbn));
        }

        // 7. Retrieve value using null key

        System.out.println("\n Book for null key : " + libraryA.get(null));


        // 8. Retrieve null value (will print "null")

        System.out.println(" Book for ISBN004 " + libraryA.get("ISBN004"));


        // 9. Create another library (Library B)

        Map<String , LibraryBook> libraryB = new HashMap<>();
        libraryB.put("ISBN005", new LibraryBook("Cloud Computing", "Dana", 2023));
        libraryB.put("ISBN006", new LibraryBook("Cybersecurity", "Eli", 2022));

        // 10. Merge all books from libraryA into libraryB

        libraryB.putAll(libraryA);

        // 11. Print final contents of libraryB

        System.out.println("\nMerged Library B (after putAll):");

        for (String isbn : libraryB.keySet())
        {

            System.out.println(" ISBN " + isbn + " -> " + libraryB.get(isbn));
        }



    }


}
