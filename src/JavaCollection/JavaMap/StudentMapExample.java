package JavaCollection.JavaMap;

import java.util.*;
import java.util.stream.Stream;

public class StudentMapExample
{

    public static void main(String[] args) {
        Map<String, Student> studentMap = new HashMap<>();
        studentMap.put("S001", new Student("Alice", 85.5));
        studentMap.put("S002", new Student("Bob", 90.0));
        studentMap.put("S003", new Student("Charlie", 78.2));

        // 1. Iterating Values with Iterator
        System.out.println("=== Iterating Values using Iterator ===");
        Iterator<Student> valueIterator = studentMap.values().iterator();
        while (valueIterator.hasNext()) {
            Student student = valueIterator.next();
            System.out.println(student);
        }

        // 2. Iterating Values with For-Each Loop
        System.out.println("\n=== Iterating Values using For-Each Loop ===");
        for (Student student : studentMap.values()) {
            System.out.println(student);
        }

        // 3. Iterating Values with Stream
        System.out.println("\n=== Iterating Values using Stream ===");
        Stream<Student> valueStream = studentMap.values().stream();
        valueStream.forEach(System.out::println);

        // 4. Iterating Entries with Entry Iterator
        System.out.println("\n=== Iterating Entries using Entry Iterator ===");
        Set<Map.Entry<String, Student>> entries = studentMap.entrySet();
        Iterator<Map.Entry<String, Student>> entryIterator = entries.iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Student> entry = entryIterator.next();
            String key = entry.getKey();
            Student student = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + student);
        }

        // 5. Iterating Entries with For-Each Loop
        System.out.println("\n=== Iterating Entries using For-Each Loop ===");
        for (Map.Entry<String, Student> entry : studentMap.entrySet()) {
            String key = entry.getKey();
            Student student = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + student);
        }
    }


}
