package JavaCollection.hashCode_equals;

import java.util.*;
public class LibraryApp
{

    public static void main(String[] args) {
        LibraryBook book1 = new LibraryBook("978-0134685991", "Effective Java", "Joshua Bloch");
        LibraryBook book2 = new LibraryBook("978-0134685991", "Effective Java (3rd Ed.)", "Joshua Bloch");

        Set<LibraryBook> catalog = new HashSet<>();
        catalog.add(book1);

        // This will not add book2 because it has the same ISBN as book1
        boolean added = catalog.add(book2);

        System.out.println("Book added? " + added); // false
        System.out.println("Catalog size: " + catalog.size()); // 1
    }


}
