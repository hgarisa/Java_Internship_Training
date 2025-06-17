package JavaCollection.JavaList;

import java.util.*;
public class EmployeeList
{

    public static void main(String[] args)
    {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", 5000));
        employees.add(new Employee("Jane", 7000));
        employees.add(new Employee("Doe", 6000));

        List<Employee> employees2 = new ArrayList<>();
        employees2.add(new Employee("Jo", 5200));
        employees2.add(new Employee("Je", 32000));
        employees2.add(new Employee("De", 3000));


//        Collections.sort(employees , new SalaryComparator());
//        employees.forEach(System.out::println);

        List<List<Employee>> empListofList = new ArrayList<List<Employee>>();
        empListofList.add(employees);
        empListofList.add(employees2);


        List<Employee> Emplist  =  empListofList.get(1);

//
        for (Employee emp  : Emplist)
        {

            System.out.println(emp.name + " , " + emp.salary);
        }

    }
}
