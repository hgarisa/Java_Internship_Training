package Java_Functional_Interfaces.Q1;
import java.util.*;
import java.util.function.*;


//Employee Salary Adjustment System

//        Scenario:
//        You are developing a payroll system for a company where HR wants to apply multiple
//        salary adjustments using Function, Predicate, and BiFunction.

//        Task:

//        Create a class Employee with fields: id, name, department, salary, and yearsOfService.

//        Write logic to:
//
//        Use a Predicate to filter employees who are eligible for bonus (more than 5 years of service).
//
//        Use a Function to apply a 10% increment to their salary.
//
//        Use a BiFunction to apply an additional performance bonus amount.
//



public class EmployeeSalaryAdjustment {

    // Employee class with fields and constructor
    static class Employee {
        int id;
        String name;
        String department;
        double salary;
        int yearsOfService;

        public Employee(int id, String name, String department, double salary, int yearsOfService) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.yearsOfService = yearsOfService;
        }

        @Override
        public String toString() {
            return name + " | Department: " + department + " | Salary: " + salary + " | Years: " + yearsOfService;
        }
    }

    public static void main(String[] args) {

        // Creating employee list
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "IT", 60000, 6),
                new Employee(2, "Bob", "Finance", 55000, 3),
                new Employee(3, "Charlie", "HR", 50000, 8)
        );

        // Predicate to check eligibility for bonus (Years > 5)
        Predicate<Employee> eligibleForBonus = e -> e.yearsOfService > 5;

        // Function to apply 10% increment
        Function<Employee, Double> applyIncrement = e -> e.salary * 1.10;

        // BiFunction to apply performance bonus
        BiFunction<Double, Double, Double> applyPerformanceBonus = (salary, bonus) -> salary + bonus;

        for (Employee e : employees) {
            if (eligibleForBonus.test(e)) {
                double incrementedSalary = applyIncrement.apply(e);
                double finalSalary = applyPerformanceBonus.apply(incrementedSalary, 2000.0);
                e.salary = finalSalary;
            }
            System.out.println(e);
        }
    }

    // Class representing an Employee object with salary adjustment functionality.
// The program filters employees eligible for bonus using Predicate.
// Then, it applies a salary increment using Function.
// Finally, it applies a performance bonus using BiFunction.

}

