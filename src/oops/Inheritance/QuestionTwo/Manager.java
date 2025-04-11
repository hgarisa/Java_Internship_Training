package oops.Inheritance.QuestionTwo;

// Subclass: Manager has an extra field - team size
class Manager extends Employee {
    private int teamSize;

    public Manager(String name, int id, int teamSize) {
        super(name, id); // Call constructor of Employee
        this.teamSize = teamSize;
    }

    // Specific behavior for managers
    public void manage() {
        System.out.println(name + " manages a team of " + teamSize);
    }
}
