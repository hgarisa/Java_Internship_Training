package JavaCollectionsPraticeQuestions.Q1;

import JavaCollectionsPraticeQuestions.Q1.Product;
import JavaCollectionsPraticeQuestions.Q2.Customer;

import java.util.*;
import java.util.List;

public class ProductMain
{
    public static void main (String[] args)
    {

        // Insert 5 custom objects into an arraylist with class name product .

        List<Product> productList = new ArrayList<>();

        Product prod1 = new Product(" Soccer Shoes"  , "footwear" , 900.00);
        Product prod2 = new Product(" T Shirts "  , "Clothing" , 560.00);
        Product prod3 = new Product(" Pants"  , "Clothing" , 400.00);
        Product prod4 = new Product(" Dumbells "  , "Gym Equipment" , 230.00);
        Product prod5 = new Product(" Laptop "  , "Electronics" , 15000.00);

        productList.add(prod1);
        productList.add(prod2);
        productList.add(prod3);
        productList.add(prod4);
        productList.add(prod5);

//        productList.add(new Product(" Soccer Shoes"  , "footwear" , 900.00));
//        productList.add(new Product(" T Shirts "  , "Clothing" , 560.00));
//        productList.add(new Product(" Pants"  , "Clothing" , 400.00));
//        productList.add(new Product(" Dumbells "  , "Gym Equipment" , 230.00));
//        productList.add(new Product(" Laptop "  , "Electronics" , 15000.00));
//

        //Iterate the items.

        for (  Product product : productList)
        {
            System.out.println(product);
        }

        System.out.println( " Array list size is   " + productList.size());

        System.out.println("---------------------------------------------------------------------");

        //If the size of the array is 5 then add another 2 elements to that arraylist.

        if(productList.size() == 5)
        {
            productList.add(new Product(" Cellphone "  , "Electronics" , 10000.00));
            productList.add(new Product(" PS5 "  , "Electronics" , 20000.00));

        }

//        productList.add(new Product(" Cellphone "  , "Electronics" , 10000.00));
//        productList.add(new Product(" PS5 "  , "Electronics" , 20000.00));

        for( Product product : productList)
        {
            System.out.println(product);
        }
        System.out.println( " Array list size is  now " + productList.size());

        System.out.println("------------------------------------------------");

    //  6. Do a search and get the object on the 3rd index and modify that value , insert it back on the third index.

        // (1) Way

//        Product get3rdIndexProduct = productList.get(3);
//        //System.out.println(get3rdIndexProduct);
//        Product modifiedProduct = new Product(" Punching Bag "  , "Gym Equipment" , 200.00);
//
//        productList.set(3 , modifiedProduct);
//
//        System.out.println(" Updated Product List : ");
//        System.out.println("-----------------------------------------------");
//
//        for ( Product product : productList)
//        {
//            System.out.println(product);
//        }
//
//        System.out.println("-------------------------------------------------");

        // (2nd) Way

//        Product getProductAtIndex = productList.get(3);
//        getProductAtIndex.setName("Kettle Bell");
//        getProductAtIndex.setCategory("Gym Equipment");
//        getProductAtIndex.setPrice(1000.00);
//
//        System.out.println(" Updated Product List : ");
//        System.out.println("-----------------------------------------------");
//        for ( Product product : productList)
//        {
//            System.out.println(product);
//        }


        // (3rd) Way
//        prod4.setName("Kettle Bell");
//        prod4.setCategory("Gym Equipment");
//        prod4.setPrice(1000.00);
//
//        System.out.println(" Updated Product List : ");
//        System.out.println("-----------------------------------------------");
//        for ( Product product : productList)
//        {
//            System.out.println(product);
//        }


        //Print out a product where product descrption/name is a shoe

//        for (Product product : productList)
//        {
//            if(product.getName().contains("Shoe"))
//            {
//                System.out.println(product);
//
//            }
//        }


       // If the size of the array is 5 then add another 2 elements to that arraylist.


        Customer c1 = new Customer(901 , "sfsfs" , "012910");
        System.out.println(c1);


    }
}
