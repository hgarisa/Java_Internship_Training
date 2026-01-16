package Generics_Pratice_Questions.E_Commerce_Recommendation;

public class Book extends Product
{

    public String genre ;
    public Book(String name, double price , String genre)
    {
        super(name, price);
        this.genre = genre;
    }
    @Override
    public String getCategory() {
        return genre;
    }

    public String toString()
    {

        return "Book : " + getName() + " , $ " + getPrice() + " , Genre : " + genre ;

    }
}
