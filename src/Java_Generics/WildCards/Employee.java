package Java_Generics.WildCards;

public class Employee
{

    protected String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }


    public double calculatePay() {
        return baseSalary;
    }

    @Override
    public String toString() {
        return name + " - $" + calculatePay();
    }


    public String getInfo() {
        return name + " - $" + calculatePay();
    }


}
