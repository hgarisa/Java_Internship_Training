package JavaCollection.JavaMap;

import java.util.*;
public class EmployeeMapOperations
{
    public static void main(String[] args)
    {


        Map<String, Employee> employeeMap = new HashMap<>();

        // Initial put
        employeeMap.put("E001", new Employee("Alice", "HR"));
        employeeMap.put("E002", new Employee("Bob", "Finance"));
        employeeMap.put("E003", new Employee("Charlie", "IT"));

        System.out.println("Initial Map: " + employeeMap);

        // --- Removing a specific entry ---
        employeeMap.remove("E002");
        System.out.println("\nAfter removing E002: " + employeeMap);

        // --- Replacing an existing entry ---
        employeeMap.replace("E003", new Employee("Charlie", "Operations"));
        System.out.println("\nAfter replacing E003's department: " + employeeMap);

        // --- Checking map size and emptiness ---
        System.out.println("\nMap size: " + employeeMap.size());
        System.out.println("Is map empty? " + employeeMap.isEmpty());

        // --- Functional Operations ---

        // compute(): convert Charlie's department to uppercase
        employeeMap.compute("E003", (key, emp) -> {
            if (emp != null) emp.setDepartment(emp.getDepartment().toUpperCase());
            return emp;
        });
        System.out.println("\nAfter compute() on E003: " + employeeMap);

        // computeIfAbsent(): Add a new employee if not present
        employeeMap.computeIfAbsent("E004", key -> new Employee("David", "Legal"));
        System.out.println("\nAfter computeIfAbsent(): " + employeeMap);

        // computeIfPresent(): Update department if employee exists
        employeeMap.computeIfPresent("E001", (key, emp) -> {
            emp.setDepartment(emp.getDepartment() + " & Admin");
            return emp;
        });
        System.out.println("\nAfter computeIfPresent() on E001: " + employeeMap);

        // merge(): Append to existing department or add new employee
        employeeMap.merge("E003", new Employee("Charlie", "Compliance"), (oldVal, newVal) -> {
            oldVal.setDepartment(oldVal.getDepartment() + ", " + newVal.getDepartment());
            return oldVal;
        });
        System.out.println("\nAfter merge() on E003: " + employeeMap);

        // --- clear all entries ---
        employeeMap.clear();
        System.out.println("\nAfter clearing map: " + employeeMap);
        System.out.println("Is map empty now? " + employeeMap.isEmpty());


    }

    }


