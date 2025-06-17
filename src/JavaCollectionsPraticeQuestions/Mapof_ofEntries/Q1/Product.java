package JavaCollectionsPraticeQuestions.Mapof_ofEntries.Q1;

public class Product
{
    public  String name ;
    public  Double price ;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }


    @Override
    public String toString()
    {
        return "Name :  " +  name + " , Price : " + price ;
    }
}
