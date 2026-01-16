package Inner_Outer_Classes.Easy_level_Questions.Car_Engine;

public class Car
{
   private String modelName ;

    public Car(String modelName)
    {
        this.modelName = modelName;
    }

    // Non-Static Inner Class
    public class Engine
    {

        public void startEngine()
        {

            System.out.println(" Engine of " + modelName + " is starting ... " );
        }

    }

}
