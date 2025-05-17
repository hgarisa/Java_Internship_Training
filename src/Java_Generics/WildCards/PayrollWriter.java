package Java_Generics.WildCards;


import java.util.*;

public class PayrollWriter {


    // Can add Manager or subclass to a list of Manager, Employee, or Object
    public static void addManagers(List<? super Manager> list) {
        list.add(new Manager("Alice", 7000, 1000));
        list.add(new Manager("Bob", 7500, 1200));

        // Safe to add Manager or subclasses (if any)

        //  Unsafe to read as Manager — only safe to read as Object
        Object obj = list.get(0); //  OK
        // Manager m = list.get(0); // Compile-time error
    }
}