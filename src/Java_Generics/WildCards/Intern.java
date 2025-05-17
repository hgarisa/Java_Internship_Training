package Java_Generics.WildCards;

public class Intern extends Employee
{

    public Intern(String name, double stipend) {
        super(name, stipend);
    }

    public String getInfo() {
        return name + " (Intern) - $" + baseSalary;
    }

}
