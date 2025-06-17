package Java_Lambda_Expressions.ComplexQuestions.Q5;

public class Student
{

    public String name ;
    public int gradeLevel ;
    public double score ;
    public Student(String Name ,  int GradeLevel , double Score  )
    {
        this.name = Name;
        this.gradeLevel = GradeLevel;
        this.score = Score;
    }

    public String getName() {
        return name;
    }
    public int getGradeLevel() {
        return gradeLevel;
    }

    public double getScore() {
        return score;
    }


    @Override
    public String toString() {
        return " Name : " + name + " Grade level : " + gradeLevel + " Score : " + score;
    }
}
