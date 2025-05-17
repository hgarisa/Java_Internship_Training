package Java_Generics.WildCards;

import java.util.*;

public class PayrollService
{

    // Read-only access: accepts List of Employee or any subclass
    public static void printPayroll(List<? extends Employee> employees) {
        System.out.println("--- Payroll Report ---");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    // Write-only: allows adding Manager or subclass to List of Manager, Employee, or Object
    public static void addDefaultManagers(List<? super Manager> managers) {
        managers.add(new Manager("Default Manager", 7000, 1000));
    }

    // Completely unknown type: only reading as Object
    public static void auditLog(List<?> records) {
        System.out.println("--- Audit Log ---");
        for (Object o : records) {
            System.out.println("Reviewed: " + o.toString());
        }
    }


}
