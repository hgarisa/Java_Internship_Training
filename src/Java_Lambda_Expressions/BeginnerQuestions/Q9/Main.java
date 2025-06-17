package Java_Lambda_Expressions.BeginnerQuestions.Q9;


import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args)
    {

     /*   You have a list of Employee objects. For each employee:
    Use a Predicate<Employee> to check if the employee is eligible for a bonus (e.g., salary > 50,000 and experience > 5 years).
        If eligible, use a Function<Employee, String> to extract their name and format a message like:
        "Alice is eligible for bonus!"
        Print this message only for eligible employees.*/

        List<Employee> mylist = new ArrayList<>();
        Employee e1 = new Employee("Hrudhay" , 50923.00 , 3);
        Employee e2 = new Employee("Jordan" , 60233.33 , 5);
        Employee e3 = new Employee("Casey" , 42333.012 , 2);
        Employee e4 = new Employee("Maddie" , 30000.00 , 1);
        mylist.add(e1);
        mylist.add(e2);
        mylist.add(e3);
        mylist.add(e4);
        Predicate<Employee> checkEligiblityForBonus = e -> e.getSalary() >  50000 && e.getYearsOfExperience() > 2 ;
        Function<Employee , String> printBonusEmployee = (e) -> e.getName() + " is eligible for bonus " ;

        //eligibleEmployees(mylist , checkEligiblityForBonus , printBonusEmployee); // Way (1)


        mylist.stream().filter(checkEligiblityForBonus).map(printBonusEmployee).forEach(System.out::println); // Way(2)

        }

        // Way (1)
      /*  public static void eligibleEmployees(List<Employee> list , Predicate<Employee> employeePredicate , Function<Employee , String> empFunction)
        {
            for (Employee e: list)
            {
                if (employeePredicate.test(e))
                {
                    String msg = empFunction.apply(e);
                    System.out.println(msg);
                }
            }
        }*/



}