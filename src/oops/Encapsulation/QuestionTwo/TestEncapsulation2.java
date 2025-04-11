package oops.Encapsulation.QuestionTwo;

public class TestEncapsulation2
{

    public static void main(String[] args) {
        Student s1 = new Student("John", 16);

        s1.setGrade(85.5);                 // Valid grade
        System.out.println(s1.getReportCard());

        s1.setGrade(-10);                 // Invalid grade, won't update
        System.out.println("Final Grade: " + s1.getGrade());
    }


}
