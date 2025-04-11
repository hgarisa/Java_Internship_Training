package oops.Abstraction.QuestionThree;

// Contract-based employee with hourly wage
public class ContractEmployee extends Employee
{

    private double hourlyRate;
    private int hoursWorked;

    public ContractEmployee(String name, int id, double rate, int hours) {
        super(name, id);
        this.hourlyRate = rate;
        this.hoursWorked = hours;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }


}
