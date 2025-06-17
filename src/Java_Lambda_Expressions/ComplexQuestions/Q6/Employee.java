package Java_Lambda_Expressions.ComplexQuestions.Q6;

public class Employee
{
    public String name;
    public String department;
    public double salary;
    public String location;
    public Employee(String name, String department , double salary, String location) {

        this.name = name;
        this.department = department;
        this.salary = salary;
        this.location = location;
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

    public String getlocation() {
        return location;
    }

    @Override
    public String toString() {
        return  name + " [ Department : " +  " Salary = " + salary + ",  Location  " + location ;
    }
}
