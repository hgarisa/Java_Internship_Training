package Generics_Collections;

import java.util.*;

public class UtilityClassExample
{
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Zara");
        names.add("Anna");
        names.add("Ben");

        // Using utility methods from Collections class
        Collections.sort(names); // Sort alphabetically
        System.out.println("Sorted: " + names); // [Anna, Ben, Zara]

        Collections.reverse(names); // Reverse the order
        System.out.println("Reversed: " + names); // [Zara, Ben, Anna]
    }
}
