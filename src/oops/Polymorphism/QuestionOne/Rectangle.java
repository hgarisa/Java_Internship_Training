package oops.Polymorphism.QuestionOne;

// Rectangle is another specific implementation of Shape
class Rectangle extends Shape {
    private double width, height;

    public Rectangle(double w, double h) {
        this.width = w;
        this.height = h;
    }

    // Override area() for rectangle
    @Override
    double area() {
        return width * height;
    }

    // Override perimeter() for rectangle
    @Override
    double perimeter() {
        return 2 * (width + height);
    }
}
