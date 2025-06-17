package Java_Lambda_Expressions.ComplexQuestions.Q5;

import java.util.*;
/*
You're generating progress reports. Each Student has:
        Name
        Grade Level
        Score
         Exercise:

        Use a lambda expression to transform each student into a sentence:

        "Alice from grade 12 scored 91.5 and passed."

        Consider passing if score >= 60

        Display all student reports using map() and forEach().

*/

public class Main
{
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        Student student1 = new Student("Jason", 10, 77.20);
        Student student2 = new Student("Hrudhay", 8, 80.00);
        Student student3 = new Student("Dew", 11, 56.2);
        Student student4 = new Student("Roman", 9, 70.00);
        Student student5 = new Student("Mathew", 12, 44.3);

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);

     students.stream().map(student -> student.getName() + " from grade " + student.getGradeLevel() + " scored " +
       student.getScore() + " and " + (student.getScore() >= 60  ? " passed " : " failed ")
     ).forEach(System.out::println);



    }
}
