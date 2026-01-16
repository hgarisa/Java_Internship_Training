package Inner_Outer_Classes.Easy_level_Questions.Car_Engine;
import java.util.*;

/*
 Problem Recap:
You need to:
Create a Car class with a field modelName.

Define a non-static inner class Engine.

The Engine class should have a method startEngine() that prints:

"Engine of <modelName> is starting...".

Instantiate the inner class and call the method.

* */
public class Main
{
    public static void main(String[] args)
    {

        Car car = new Car("Toyota Corolla");

        // To create an Instance of an inner class

        Car.Engine engine = car.new Engine();

        engine.startEngine(); // Output : Engine of Toyota Corolla is starting...
    }
}
