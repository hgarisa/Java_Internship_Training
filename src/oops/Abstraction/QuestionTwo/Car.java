package oops.Abstraction.QuestionTwo;

public class Car implements Vehicle
{


    private int speed;

    @Override
    public void start() {
        speed = 10;
        System.out.println("Car started.");
    }

    @Override
    public void stop() {
        speed = 0;
        System.out.println("Car stopped.");
    }

    @Override
    public int getSpeed() {
        return speed;
    }
}
