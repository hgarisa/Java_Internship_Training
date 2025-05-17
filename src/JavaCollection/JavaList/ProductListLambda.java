package JavaCollection.JavaList;


import java.util.*;
public class ProductListLambda
{
    public static void main(String[] args)
    {

        List<ProductLambda> products = new ArrayList<>();

        products.add(new ProductLambda("TV", 4.1));
        products.add(new ProductLambda("Refrigerator", 4.8));
        products.add(new ProductLambda("Washing Machine", 4.4));


        // Sort by rating descending

      products.sort((p1,p2) -> Double.compare(p2.rating , p1.rating));
        products.forEach(System.out::println);


        // Sort by rating Ascending

//        products.sort((p1,p2) -> Double.compare(p1.rating , p2.rating));
//        products.forEach(System.out::println);



    }

}
