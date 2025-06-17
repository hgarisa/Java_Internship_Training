package JavaCollectionsPraticeQuestions.Mapof_ofEntries.Q1;

import java.util.Map;

public class ProductMapExample
{
    public static void main (String[] args)
    {


        // Map.of()
        Map<String, Product> productMap = Map.of(
                "P001" , new Product("Laptop" , 99.00),
                "P002" , new Product("Keyboard", 49.99),
                "P003" , new Product("Mouse", 25.75)
        );

        productMap.forEach((Code, product) -> System.out.println(" Product code : " + Code + " => Product : " + product ));


        System.out.println("--------------------------------------------------------------------------------------------------------");
        // Map.ofEntries()

        Map<String , Product> productMap2 = Map.ofEntries(

                Map.entry("P001" , new Product("PS5" , 12432.919)),
                Map.entry("P002" , new Product("XBOX" , 12533.932)) ,
                Map.entry("P003" , new Product("Nintendo" , 8332.90))

        );
        productMap2.forEach((code , product) -> System.out.println(" Product code : " + code + " => Product " + product ));

    }
}
