package oops.Inheritance.QuestionTwo;

// Base class: Generic employee
class Employee {
    protected String name;
    protected int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Common behavior to all employees
    public void displayDetails() {
        System.out.println("Name: " + name + ", ID: " + id);
    }
}