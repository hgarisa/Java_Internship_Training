package oops.Polymorphism.QuestionOne;

class ShapePrinter {
    // Accepts any shape and prints area and perimeter
    public static void printShapeInfo(Shape shape) {
        System.out.println("Area: " + shape.area());
        System.out.println("Perimeter: " + shape.perimeter());
    }



    public static void main (String []args)
    {

        Shape shape1 = new Circle(5);       // Runtime type: Circle
        Shape shape2 = new Rectangle(4, 6); // Runtime type: Rectangle

        // Calls the overridden methods of actual class (dynamic dispatch)
        ShapePrinter.printShapeInfo(shape1);
        ShapePrinter.printShapeInfo(shape2);

    }
}
