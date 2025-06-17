package Java_Nested_Classes.Q1;
import java.util.*;

/*

University Course Registration System

Problem:

Design a system to handle university course registrations.

Each Course can have many Students.

Each Student has an Address.

Use nested classes where appropriate to represent:

Address as an inner class of Student.

Student as an inner class of Course.

Include methods to:

Add students to a course.

Display course details along with enrolled students and their addresses.

 */

public class Course {

    private String courseId;
    private String courseName;
    private List<Student> enrolledStudents;

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.enrolledStudents = new ArrayList<>();
    }

    // Method to add a student to the course
    public void addStudent(Student student) {
        enrolledStudents.add(student);
    }

    // Method to display course details
    public void displayCourseInfo() {
        System.out.println("Course ID: " + courseId);
        System.out.println("Course Name: " + courseName);
        System.out.println("Enrolled Students:");
        for (Student s : enrolledStudents) {
            s.displayStudentInfo();
        }

    }

    // Nested Inner Class: Student
    public class Student {
        private String studentId;
        private String studentName;
        private Address address;

        public Student(String studentId, String studentName, Address address) {
            this.studentId = studentId;
            this.studentName = studentName;
            this.address = address;
        }

        public void displayStudentInfo() {
            System.out.println("\tStudent ID: " + studentId);
            System.out.println("\tStudent Name: " + studentName);
            address.displayAddress();
        }

        public class Address {
            private String street;
            private String city;
            private String postalCode;

            public Address(String street, String city, String postalCode) {
                this.street = street;
                this.city = city;
                this.postalCode = postalCode;
            }

            public void displayAddress() {
                System.out.println("\tAddress: " + street + ", " + city + " - " + postalCode);
            }
        }

    }

    public static void main(String[] args) {
        Course course = new Course("C101", "Java Programming");

        // Create Address using outer instance of Student
        Course.Student student1 = course.new Student("S001", "John Doe",
                course.new Student("S001", "John Doe", null).new Address("123 Main St", "New York", "10001"));

        course.addStudent(student1);
        course.displayCourseInfo();
    }




}
