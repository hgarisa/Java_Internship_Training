package Java_Lambda_Expressions.ComplexQuestions.Q11;

public class Employee
{
    public String name;


    public String department;
    public double salary;
    public int yearsOfExperience;

    public Employee(String name, String department , double salary, int yearsOfExperience) {

        this.name = name;
        this.department = department;
        this.salary = salary;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }


    public double getSalary() {
        return salary;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    @Override
    public String toString() {
        return  name + " [ Department : " +  " Salary = " + salary + ",  Experience= " + yearsOfExperience + " years]";
    }
}
