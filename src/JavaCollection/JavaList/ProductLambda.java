package JavaCollection.JavaList;

public class ProductLambda
{

    // We'll sort by rating using a lambda expression.

    public String name;
    public double rating;

    public ProductLambda(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    public String toString() {
        return " Name " + name + " ,  Rating : " + rating  ;
    }


}
