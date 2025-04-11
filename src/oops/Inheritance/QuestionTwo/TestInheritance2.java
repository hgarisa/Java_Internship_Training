package oops.Inheritance.QuestionTwo;

public class TestInheritance2
{

    public static void main(String[] args) {
        Developer dev = new Developer("Alice", 101, "Java");
        Manager mgr = new Manager("Bob", 102, 5);

        dev.displayDetails(); // Inherited from Employee
        dev.code();           // Defined in Developer

        mgr.displayDetails(); // Inherited from Employee
        mgr.manage();         // Defined in Manager
    }
}
