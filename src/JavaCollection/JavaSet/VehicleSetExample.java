package JavaCollection.JavaSet;


import java.util.*;
public class VehicleSetExample
{
    public static void main(String[] args) {

        // Create Set of Vehicles

     Set<Vehicle> set1 = new HashSet<>();
        set1.add(new Vehicle("ABC123", "Toyota Corolla"));
        set1.add(new Vehicle("XYZ789", "Honda Civic"));
        set1.add(new Vehicle("LMN456", "Ford Ranger"));

        // -------------------------------
        // (1️) Iterate Using Iterator
        System.out.println("Iterating using Iterator:");

        Iterator<Vehicle> ite = set1.iterator();

        while (ite.hasNext())
        {

            Vehicle v = ite.next();
            System.out.println(v);
        }

        // -------------------------------
        // (2️) Iterate Using For-each Loop

        System.out.println("\nIterating using for-each loop:");

        for (Vehicle v: set1)
        {
            System.out.println(v);
        }

        // -------------------------------
        // (3️) Iterate Using Stream API
        System.out.println("\nIterating using Stream API:");
        set1.stream().forEach(vehicle -> System.out.println(vehicle));


        // -------------------------------
        // (4️) Remove One Vehicle

        System.out.println("\nRemoving vehicle with reg ABC123:");

        set1.remove(new Vehicle("ABC123" , "Any")); //Only reg number matters in equals()

        set1.forEach(System.out::println);

        // -------------------------------
        // (5️) Remove All Vehicles

        System.out.println("\nClearing all vehicles from set1:");

        set1.clear();
        System.out.println("set1 is now empty: " + set1);

        // -------------------------------
        // (6️) Add All Vehicles from Another Set

        Set<Vehicle> garage1 = new HashSet<>();

        garage1.add(new Vehicle("AAA111", "Hyundai i10"));
        garage1.add(new Vehicle("BBB222", "Chevy Spark"));

        Set<Vehicle> garage2 = new HashSet<>();
        garage2.add(new Vehicle("CCC333", "Mazda CX-5"));

        System.out.println("\nBefore adding all vehicles:");
        System.out.println("Garage 2: " + garage2);

        garage2.addAll(garage1); // Add everything from garage1 into garage2
        System.out.println("After adding garage1 into garage2:");
        garage2.forEach(System.out::println);











    }



}
