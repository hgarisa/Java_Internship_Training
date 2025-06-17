package Java_Streams.Q2_Employee_Certification_Filter;
import java.util.*;
import java.util.stream.*;

public class EmployeeFilter
{

    public static List<String> getQualifiedEmployees(List<Employee> employees) {
        return employees.stream()
                // Filter by department
                .filter(e -> e.getDepartment().equals("Technology"))
                // Check if at least 2 certifications start with "Java"
                .filter(e -> e.getCertifications().stream()
                        .filter(c -> c.startsWith("Java"))
                        .count() >= 2)
                // Map to employee names
                .map(Employee::getFullName)
                // Collect as list
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Sample data
        List<Employee> employees = List.of(
                new Employee("E1", "Alice", "Technology", List.of("Java SE 8", "Java Spring", "AWS")),
                new Employee("E2", "Bob", "Technology", List.of("Java SE 8")),
                new Employee("E3", "Charlie", "HR", List.of("Java SE 8", "Java Web"))
        );

        // Run filtering
        List<String> qualified = getQualifiedEmployees(employees);

        // Print result
        qualified.forEach(System.out::println);
    }


}
