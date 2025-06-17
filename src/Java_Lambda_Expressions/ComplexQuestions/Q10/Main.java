package Java_Lambda_Expressions.ComplexQuestions.Q10;

/*

Scenario:
You're creating a student GPA report. Each Student has:
name, major, gpa
 Exercise:
Map each student into a sentence:
"John majoring in Computer Science has a GPA of 3.8 and is in category: Distinction."
Categories:
GPA ≥ 3.7 → Distinction
GPA ≥ 3.0 → Merit
Else → Pass
Print all reports using .map() and .forEach().



* */

import java.util.*;


public class Main {
    public static void main(String[] args)
    {

List<Student> students = new ArrayList<>();

Student student1 = new Student("Hrudhay Reddy Garisa" , "Computer Science" , 3.3);
        Student student2 = new Student("Mason Mount" , "Acoounting" , 5.6);
        Student student3 = new Student("Eden Hazard" , "Law" , 2.0);
        Student student4 = new Student("Harsha Ramesh" , "Computer Science" , 1.6);
        Student student5 = new Student("James Maddison" , "Biology" , 3.5);

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);

students.stream().map(student -> student.getName() + " Majoring in " + student.getMajor() + " has a Gpa " + student.getGpa() + " and is in category : "
        + (student.gpa > 3.7 ? "Distinction" : student.gpa > 3.0 ? "Merit" : "Pass" ) ).forEach(System.out::println);




    }
}