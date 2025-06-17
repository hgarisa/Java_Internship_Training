package JavaCollectionsPraticeQuestions.Q1;

public class Product
{

    public String name ;
    public String category ;
    public Double price ;

    public Product( String name , String category , Double price  )
    {
        this.name = name;
        this.category = category;
        this.price = price;

    }

    public String getName()
    {
        return name;

    }

    public String getCategory()
    {
        return category;

    }

    public double getPrice()
    {
        return price;

    }
    public void setName(String name)
    {

        this.name = name;

    }

    public void setCategory(String category)
    {

        this.category = category;

    }

    public void setPrice(Double price)
    {

        this.price = price;

    }
    public String toString()
    {

        return " Product Name :  " +  name + " , "  + " Category :  " + category + " , " + " Price : " + price ;
    }

}
