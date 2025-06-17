package Java_Lambda_Expressions.BeginnerQuestions.Q6;

// Question Requirements

//You have a list of Employee objects, and you want to check:
//
//Who is eligible for promotion (based on salary).
//
//Who is a senior (based on years of experience).
//
//We'll use Predicate<Employee> to do this.

public class Main
{
    public static void main(String[] args)
    {

//        List<Employee> myemployees = new ArrayList<>();
//        Employee e1 = new Employee("Alice" , 6000.00 , 5);
//        Employee e2 = new Employee("Bob" , 4500.00 , 2);
//        Employee e3 = new Employee("Charlie" , 7000.00 , 7);
//        Employee e4 = new Employee("Diana" , 3000.00 , 1);
//
//        myemployees.add(e1);
//        myemployees.add(e2);
//        myemployees.add(e3);
//        myemployees.add(e4);
//
//        // Predicate to check salary > 5000
//
//        Predicate<Employee> isEligibleForPromotion = e -> e.getSalary() > 5000 ;
//
//        // Predicate to check experience > 3 years
//
//        Predicate<Employee> isSenior = e -> e.getYearsOfExperience() > 3 ;
//
//        System.out.println("--Eligible for promotion---");
//        filterEmployees(myemployees , isEligibleForPromotion);
//
//        System.out.println("--Senior Employees-----");
//        filterEmployees(myemployees , isSenior);

    }

    // Way(1) using For-Each loop and if statement

//    public static void filterEmployees(List<Employee> list , Predicate<Employee> condition)
//    {
//        for (Employee e : list)
//        {
//            if (condition.test(e))
//            {
//                System.out.println(e);
//            }
//        }
//
//    }

    // Way (2) Using .streams()
//    public static void filterEmployees(List<Employee> list , Predicate <Employee> condition)
//    {
//        list.stream().filter(condition).forEach(System.out::println);
//
//    }




}
