package Java_Generics.WildCards;

public class Manager  extends Employee
{

    private double bonus;

    public Manager(String name, double baseSalary, double bonus) {
        super(name, baseSalary);
        this.bonus = bonus;
    }

    @Override
    public double calculatePay() {
        return baseSalary + bonus;
    }

    public String getInfo() {
        return name + " (Manager) - $" + calculatePay();
    }

}
