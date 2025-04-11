package oops.Encapsulation.QuestionTwo;

public class Student
{
    private String name;
    private int age;
    private double grade;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter for grade
    public double getGrade() {
        return grade;
    }

    // Setter with validation logic
    public void setGrade(double grade) {
        if (grade >= 0 && grade <= 100) {
            this.grade = grade;
        } else {
            System.out.println("Invalid grade. Must be between 0 and 100.");
        }
    }

    public String getReportCard() {
        String status = (grade >= 50) ? "Pass" : "Fail";
        return "Student: " + name + ", Grade: " + grade + ", Status: " + status;
    }


}
