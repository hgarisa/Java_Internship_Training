package Java_Lambda_Expressions.ComplexQuestions.Q10;

public class Student
{

    public String name;
    public String major;
    public double gpa;

    public Student(String Name , String Major , double Gpa)
    {
         this.name = Name;
        this.major = Major;
        this.gpa = Gpa;

    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return " Student Name : "  + name + " Majoring in " + major + " has a " + gpa + " as in Category ";
    }
}
