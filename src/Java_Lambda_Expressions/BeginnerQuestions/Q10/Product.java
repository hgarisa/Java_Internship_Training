package Java_Lambda_Expressions.BeginnerQuestions.Q10;

public class Product
{

    public String name;
    public double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " | " + price;
    }
}
