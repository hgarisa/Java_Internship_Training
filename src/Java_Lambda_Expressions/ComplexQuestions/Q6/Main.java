package Java_Lambda_Expressions.ComplexQuestions.Q6;

import java.util.*;
import java.util.stream.Collectors;

/*You're analyzing salary data for employees in different departments.
        Each Employee has:
        name, department, salary, location
         Exercise:
        Group employees by department.
        For each department, calculate the average salary.
        Print: Department -> AverageSalary
        Bonus: Only include departments where the average salary is above 50,000.
        */

public class Main
{
    public static void main(String[] args)
    {

        List<Employee> employees = new ArrayList<>();

        Employee employee1 = new Employee("Hrudhay Garisa" , "Marketing" , 45000.00 , "South Africa");
        Employee employee2 = new Employee("Andrew Tate" , "Finance" , 150000.00 , "Romania");
        Employee employee3 = new Employee("Roman Reigns" , "HR" , 766000.000 , "Fiji");
        Employee employee4 = new Employee("Harish Rao" , "Finance" , 20000.00 , "India");
        Employee employee5 = new Employee("Neymar Jr" , "HR" , 90000.00 , "Brazil");
        Employee employee6 = new Employee("John Cena" , "Marketing" , 14000.00 , "USA");

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
        employees.add(employee6);

    employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment , Collectors.averagingDouble(Employee::getSalary)))
            .entrySet().stream().filter(entry -> entry.getValue() > 50000) // Stream of (department , avgSalary) pairs
            .forEach(entry -> System.out.println( "Department : " + entry.getKey()  + " , " + " Average Salary : " + entry.getValue()));


    }
}
