package Java_Lambda_Expressions.BeginnerQuestions.Q10;

import java.util.*;
import java.util.function.Consumer;

public class Main
{

    public static void main(String[] args)
    {

        // Q Let’s say you have a business that logs details about products in inventory.

        List<Product> myproducts = Arrays.asList(

                new Product("Laptop" , 1200.00) ,
                new Product("Phone" , 799.00),
               new Product("Monitor" , 299.49)

        );

        // Define the Consumer to log product info

        Consumer<Product> logProduct = p -> System.out.println("Logging Product : " + p);

        // Apply the Consumer to each product
        myproducts.forEach(logProduct);

    }

}
