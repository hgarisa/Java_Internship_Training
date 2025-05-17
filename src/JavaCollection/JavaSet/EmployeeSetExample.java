package JavaCollection.JavaSet;

import java.util.*;
public class EmployeeSetExample
{
    public static void main(String[] args)
    {

//    Set<Employee> employeeSet = new HashSet<>();
//        employeeSet.add(new Employee("Nathan", 101));
//        employeeSet.add(new Employee("Bob", 102));
//        employeeSet.add(new Employee("Nathan", 101)); // Duplicate by ID
//
//        for (Employee employee: employeeSet)
//        {
//            System.out.println(employee);
//        }

      //  Immutable Set with Set.of()

        Employee emp1 = new Employee("Carol", 201);
        Employee emp2 = new Employee("Dan", 202);

        Set<Employee> fixedEmployees = Set.of(emp1 , emp2);
        System.out.println(fixedEmployees);

        // fixedEmployees.add(new Employee("Eve", 203)); //  Error: UnsupportedOperationException


    }


}
