package Java_Lambda_Expressions.ComplexQuestions.Q2;
import java.util.*;
import java.util.function.Function;
import java.util.*;

/*
* You're building a feature for an e-commerce site. Each Product has:
Name
Category
Price
Rating

*Exercise:
Filter products where:

Category is "Electronics"

Rating is greater than 4.0

Sort the filtered products by price in ascending order.

Display the final sorted list.

* */

public class Main
{
    public static void main(String[] args)
    {

        List<Product> myproduct = new ArrayList<>();

        Product p1 = new Product("Soccer Ball" , "Sports" , 500.00 , 4.5);
        Product p2 = new Product("Laptop" , "Electronics" , 15499.00 , 4.0);
        Product p3 = new Product("Iphone 11" , "Electronics" , 20500.00 , 4.8);
        Product p4 = new Product("Jeans" , "Clothing" , 200.00 , 4.1);
        Product p5 = new Product("Mouse" , "Electronics" , 50.00 , 5.0);

        myproduct.add(p1);
        myproduct.add(p2);
        myproduct.add(p3);
        myproduct.add(p4);
        myproduct.add(p5);

        myproduct.stream().
                filter(p -> p.getCategoryName().equals("Electronics")
                        && p.getRating() > 4.0)
                .sorted((pr1 , pr2) -> Double.compare(pr1.getPrice() , pr2.getPrice()))
                .forEach(System.out::println);




    }
}
