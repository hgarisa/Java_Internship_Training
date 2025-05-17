package JavaCollection.GenericCollection;

import java.util.*;
public class GenericCollectionExample
{
    public static void main(String[] args) {

        // ---------------------------------------
        // (1) Generic Collection Example
        // ---------------------------------------
        Collection<String> stringCollection = new HashSet<>(); // Only stores Strings

        // Add String elements
        stringCollection.add("Java");
        stringCollection.add("Python");
        stringCollection.add("C++");

        // Uncommenting the line below will give a compile-time error:
        // stringCollection.add(123); //  Error: incompatible types

        System.out.println("Original collection: " + stringCollection);

        // ---------------------------------------
        //   (2): Generic Iterator Example
        // ---------------------------------------

        Iterator<String> ite = stringCollection.iterator();
        System.out.println("\nIterating using Iterator:");
        while (ite.hasNext())
        {
            String element = ite.next(); // No casting needed
            System.out.println("-> " + element);
        }


        // ---------------------------------------
        //  (3) : Generic For-each Loop Example
        // ---------------------------------------
        System.out.println("\nIterating using For-Each Loop:");

        for (String lang : stringCollection)
        {
            System.out.println("-> " + lang);
        }

    }

}
