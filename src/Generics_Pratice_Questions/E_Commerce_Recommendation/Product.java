package Generics_Pratice_Questions.E_Commerce_Recommendation;

public abstract class Product
{
    public String name ;
    public double price ;


    public Product(String name , double price)
    {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


    public abstract String getCategory() ;


}
