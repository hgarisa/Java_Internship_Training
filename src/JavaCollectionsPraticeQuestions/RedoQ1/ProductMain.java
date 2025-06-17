package JavaCollectionsPraticeQuestions.RedoQ1;


import java.util.*;
public class ProductMain
{

    public static void main(String[] args)
    {



        // Q1 Inserts 5 custom objects into an arraylist with class name product .


        List<Product> productList1 = new ArrayList<>();

        Product prod1 = new Product("Laptop" , "Electronics" , 20000.00);
        Product prod2 = new Product("Iphone" , "Electronics" , 10000.00);
        Product prod3 = new Product("Soccer Shoes" , "Footwear" , 800.00);
        Product prod4 = new Product("T shirts" , "Clothing" , 130.00);
        Product prod5 = new Product("Dumbell" , "Gym Equipment" , 500.00);


        productList1.add(prod1);
        productList1.add(prod2);
        productList1.add(prod3);
        productList1.add(prod4);
        productList1.add(prod5);

 //Iterate the items.

//        for (Product product: productList1)
//        {
//            System.out.println(product);
//        }
//
//        System.out.println( "Size of the array list is  " + productList1.size());
//        System.out.println("------------------------------------------------------------------------------");

// Print out a product where product description is a shoe

//        for (Product product: productList1)
//        {
//            if (product.getName().toLowerCase().contains("shoe"))
//            {
//
//                System.out.println(product);
//            }
//
//        }

       // If the size of the array is 5 then add another 2 elements to that arraylist.


//        if(productList1.size() == 5)
//        {
//            productList.add(new Product("PS5" , "Electronics" , 20000.00));
//            productList.add(new Product("Xbox" , "Electronics" , 23000.00));
//        }
//
//        System.out.println("Updated list");
//
//        for (Product product: productList1)
//        {
//            System.out.println(product);
//        }
//        System.out.println( "Size of the array list is now " + productList.size());



    // Do a search and get the object on the 3rd index and modify that value , insert it back on the third index.

        // Method (1)
//        Product getProductIndex3 = productList1.get(3);
//        //System.out.println("Product at index 3 is " + productList.get(3));
//
//        Product modifiedProduct = new Product("Shirt" , "Clothing" , 230.00);
//
//        productList1.set(3 , modifiedProduct);
//
//        System.out.println("-------------------------------------------------------------------------");
//        System.out.println(" Recent Updated list");
//
//        for (Product product: productList1)
//        {
//            System.out.println(product);
//        }
//        System.out.println( "Size of the array list is still " + productList1.size());

        // Way(2)
//        Product getProductIndex3 = productList1.get(3);
//
//        getProductIndex3.setName("Shirt");
//        getProductIndex3.setCategory("Clothing");
//        getProductIndex3.setPrice(230.00);
//
//        System.out.println("-------------------------------------------------------------------------");
//        System.out.println(" Recent Updated list");
//
//        for (Product product: productList1)
//        {
//            System.out.println(product);
//        }
//        System.out.println( "Size of the array list is still " + productList1.size());



        List<Product> productList2 = new ArrayList<>();

        Product prod8 = new Product("Socks" , "Clothing" , 100.00);
        Product prod9 = new Product("Pencil" , "Stationery" , 2.00);

        productList2.add(prod8);
        productList2.add(prod9);

//        for (Product product: productList2)
//        {
//            System.out.println(product);
//        }


        // Create an arraylist of size 2 in which in the arraylist each object is an arraylist .


        List<List<Product>> myProductList = new ArrayList<>();

        myProductList.add(productList1);
        myProductList.add(productList2);

        // Iterating the items
//        for (List<Product> list : myProductList)
//        {
//            System.out.println(list);
//
//        }

        // Iterate the values of index 1 object only .


//        List<Product> productlist2 = myProductList.get(1);
//
//        for ( Product products : productlist2)
//        {
//            System.out.println(products);
//
//        }

    }
}
