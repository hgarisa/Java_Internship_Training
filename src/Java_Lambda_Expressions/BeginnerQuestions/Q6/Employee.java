package Java_Lambda_Expressions.BeginnerQuestions.Q6;

public class Employee
{
    public String name;


    public double salary;
    public int yearsOfExperience;

    public Employee(String name, double salary, int yearsOfExperience) {
        this.name = name;
        this.salary = salary;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    @Override
    public String toString() {
        return name + " [Salary = " + salary + ",  Experience= " + yearsOfExperience + " years]";
    }
}
