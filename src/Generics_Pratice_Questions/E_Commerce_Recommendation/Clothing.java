package Generics_Pratice_Questions.E_Commerce_Recommendation;

public class Clothing extends Product
{

public String type ;
    public Clothing(String name, double price , String type)
    {
        super(name, price);
        this.type = type;
    }

    @Override
    public String getCategory() {
        return type ;
    }



    public String toString()
    {
       return " Clothing : " + getName() + " , $ " + getPrice() + " , Type : " + type ;
    }




}
