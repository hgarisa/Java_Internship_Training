package Java_Lambda_Expressions.ComplexQuestions.Q2;

public class Product
{
    public String name ;
    public String categoryName ;
    public double price ;
    public double rating ;

    public Product (String Name  , String categoryName  , double Price , double Rating )
    {
        this.name = Name;
        this.categoryName = categoryName;
        this.price = Price;
        this.rating = Rating;

    }

    public String getName() {
        return name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return " Product name : " + name + " Category Name : " + categoryName + " Price : " + price + " Rating : " + rating;
    }
}



