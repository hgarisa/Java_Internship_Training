package Java_Generics.WildCards;

import java.util.*;
public class PayrollReader
{


    // Can read details from any list of Employee or subclass
    public static void printPayroll(List<? extends Employee> employees) {
        for (Employee e : employees) {
            System.out.println(e.getInfo());
        }

        //  Not allowed: cannot insert (even if it's Manager or Intern)
        // employees.add(new Manager("New Manager", 8000, 2000));
    }
}
