package oops.Abstraction.QuestionTwo;

// Concrete implementation: Airplane
public class Airplane implements Vehicle
{

    private int speed;

    @Override
    public void start() {
        speed = 300;
        System.out.println("Airplane taking off...");
    }

    @Override
    public void stop() {
        speed = 0;
        System.out.println("Airplane landed.");
    }

    @Override
    public int getSpeed() {
        return speed;
    }

}
