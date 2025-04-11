package oops.Polymorphism.QuestionOne;

// Circle is a specific implementation of Shape
class Circle extends Shape {
    private double radius;

    public Circle(double r) {
        this.radius = r;
    }

    // Override the abstract method for Circle's area
    @Override
    double area() {
        return Math.PI * radius * radius;
    }

    // Override the abstract method for Circle's perimeter
    @Override
    double perimeter() {
        return 2 * Math.PI * radius;
    }
}
