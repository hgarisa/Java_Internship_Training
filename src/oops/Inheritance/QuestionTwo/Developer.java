package oops.Inheritance.QuestionTwo;

// Subclass: Developer has an extra field - programmingLanguage
class Developer extends Employee {
    private String programmingLanguage;

    public Developer(String name, int id, String language) {
        super(name, id); // Call constructor of Employee
        this.programmingLanguage = language;
    }

    // Specific behavior for developers
    public void code() {
        System.out.println(name + " is coding in " + programmingLanguage);
    }
}