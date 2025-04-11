package oops.Abstraction.QuestionTwo;

public class TestAbstraction2
{

    public static void main(String[] args) {
        Vehicle car = new Car();
        Vehicle plane = new Airplane();

        car.start();
        System.out.println("Car Speed: " + car.getSpeed());
        car.stop();

        plane.start();
        System.out.println("Plane Speed: " + plane.getSpeed());
        plane.stop();
    }

}
