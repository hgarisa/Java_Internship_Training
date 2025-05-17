package JavaCollection.JavaComparable;

import java.util.*;

public class Hospital
{
    public static void main(String[] args)
    {


        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("P102", "Alice", 30));
        patients.add(new Patient("P105", "Bob", 25));
        patients.add(new Patient("P101", "Charlie", 30));
        patients.add(new Patient("P103", "Alice", 22));

        System.out.println("Before sorting:");
        patients.forEach(System.out::println);

        Collections.sort(patients);

        System.out.println("\nAfter sorting by age, then name:");
        patients.forEach(System.out::println);

        // Use in HashSet
        Set<Patient> uniquePatients = new HashSet<>(patients);
        System.out.println("\nUnique patients in HashSet:");
        uniquePatients.forEach(System.out::println);
    }


    }

