package JavaCollection.JavaList;

import java.util.*;
public class StudentListIterationDemo
{
    public static void main(String[] args)
    {

        // Create a list of Student objects

        List<Student> students = new ArrayList<>();

        students.add(new Student("Alice", 20));
        students.add(new Student("Bob", 22));
        students.add(new Student("Charlie", 19));
        students.add(new Student("Hrudhay", 23));
        students.add(new Student("Virat", 36));



        System.out.println("----- 1. Iteration using Iterator -----");
        Iterator<Student> ite = students.iterator();
        while (ite.hasNext())
        {
            Student student = ite.next();
            System.out.println(student);
        }

        System.out.println("\n----- 2. Iteration using For-Each Loop -----");

        for (Student student: students)
        {
            System.out.println(student);
        }

        System.out.println("\n----- 3. Iteration using For Loop with Index -----");

        for (int i = 0 ; i < students.size(); i++)
        {
            Student student = students.get(i);
            System.out.println("Student # " + (i + 1) + " : " + student );

        }

        System.out.println("\n----- 4. Iteration using Java Stream API -----");

        students.stream().forEach(student -> System.out.println(student));


        System.out.println("\n----- Filter Students Aged 21 and Above (Stream API) -----");

        students.stream().filter(student -> student.age >= 21).forEach(System.out::println);

    }
}
