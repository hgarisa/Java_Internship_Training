package oops.Abstraction.QuestionThree;

public class TestAbstraction3
{

    public static void main(String[] args) {
        Employee fullTime = new FullTimeEmployee("Alice", 101, 6000);
        Employee contract = new ContractEmployee("Bob", 102, 50, 160);

        fullTime.displayInfo();
        System.out.println("Salary: $" + fullTime.calculateSalary());

        contract.displayInfo();
        System.out.println("Salary: $" + contract.calculateSalary());
    }

}
