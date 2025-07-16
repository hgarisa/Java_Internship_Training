package Java_IO.Streams.CSV_Employee_Data;

public class Employee
{

    private String name;
    private String department;
    private double salary;
    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String toString() {
        return String.format(" %s | Dept: %s | Salary: $%.2f", name, department, salary);
    }


}
