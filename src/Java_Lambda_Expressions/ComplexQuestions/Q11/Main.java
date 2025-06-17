package Java_Lambda_Expressions.ComplexQuestions.Q11;
import java.util.*;

/*
Scenario:
You are managing a list of employees. Each Employee has:
Name
Department
Salary
Years of experience
 Exercise:
Filter employees from the "Technology" department who has more than 5 years of experience.
Apply a 10% salary increase only to those filtered employees.
Print out the updated employee list with salaries.

* */

public class Main {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

      Employee e1 = new Employee("Hrudhay", "Technology", 120000.00, 6);
        Employee e2 = new Employee("Harry", "Technology", 150000.00, 8);
      Employee e3 = new Employee("Nathan", "Technology", 400000.00, 10);
       Employee e4 = new Employee("George", "Marketing", 90000.00, 2);
        Employee e5 = new Employee("Sancho", "Technology", 60000.00, 1);
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);
        employees.add(e5);

        employees.stream().filter(employee -> employee.getDepartment().equals("Technology") && employee.getYearsOfExperience() > 5)
                .forEach(employee -> employee.salary = (employee.getSalary()) * (0.1) + (employee.getSalary()));

        System.out.println("Updated Employee List");
        employees.forEach(System.out::println);


    }
}