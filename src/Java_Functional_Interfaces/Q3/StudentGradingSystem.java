package Java_Functional_Interfaces.Q3;

import java.util.*;
import java.util.function.*;


//   Student Grading System

//        Scenario:
//        You are developing a grading system that computes final grades based on multiple criteria
//        using functional interfaces.
//
//        Task:
//        Create class Student with fields:
//        studentId, name, midTermScore, finalExamScore, attendancePercentage.
//
//        Use:
//
//        BiFunction<Integer, Integer, Double> to calculate average score.
//
//        Predicate<Double> to check pass or fail condition.
//
//        Function<Double, String> to convert score into grade category.
//

public class StudentGradingSystem {

    static class Student {
        int studentId;
        String name;
        int midTermScore;
        int finalExamScore;
        double attendancePercentage;

        public Student(int studentId, String name, int midTermScore, int finalExamScore, double attendancePercentage) {
            this.studentId = studentId;
            this.name = name;
            this.midTermScore = midTermScore;
            this.finalExamScore = finalExamScore;
            this.attendancePercentage = attendancePercentage;
        }

        @Override
        public String toString() {
            return name + " | Midterm: " + midTermScore + " | Final: " + finalExamScore + " | Attendance: " + attendancePercentage;
        }
    }

    public static void main(String[] args) {

        List<Student> students = Arrays.asList(
                new Student(1, "John", 80, 90, 95),
                new Student(2, "Sara", 60, 65, 85),
                new Student(3, "Mike", 40, 50, 70)
        );

        BiFunction<Integer, Integer, Double> calculateAverage = (mid, fin) -> (mid * 0.4 + fin * 0.6);

        Predicate<Double> isPassed = avg -> avg >= 50;

        Function<Double, String> gradeConverter = avg -> {
            if (avg >= 85) return "A";
            else if (avg >= 70) return "B";
            else if (avg >= 50) return "C";
            else return "Fail";
        };

        students.forEach(student -> {
            double average = calculateAverage.apply(student.midTermScore, student.finalExamScore);
            boolean passed = isPassed.test(average);
            String grade = gradeConverter.apply(average);
            System.out.println(student.name + " | Average: " + average + " | Grade: " + grade + " | Passed: " + passed);
        });
    }

    // The Student class holds scores and attendance records.
// BiFunction calculates the average score.
// Predicate checks if the student passed based on score.
// Function maps the score to grade (A, B, C, Fail etc.).

}

