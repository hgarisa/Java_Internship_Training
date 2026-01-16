package Inner_Outer_Classes.Easy_level_Questions.Laptop_Processor;

public class Laptop
{
    private String brand;
    private double price;

    public Laptop(String brand , double price)
    {
        this.brand = brand;
        this.price = price;
    }

    public class Processor
    {

       public void showProcessorDetails()
       {
           System.out.println("This is a processor of a " + brand + " laptop priced at " + price );
       }

    }

}
