package oops.Abstraction.QuestionThree;


// Abstract base class: common to all employees
abstract  class Employee
{

    protected String name;
    protected int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Abstract method - must be implemented by all types
    public abstract double calculateSalary();

    // Common method to display employee info
    public void displayInfo() {
        System.out.println("Name: " + name + ", ID: " + id);
    }


}
