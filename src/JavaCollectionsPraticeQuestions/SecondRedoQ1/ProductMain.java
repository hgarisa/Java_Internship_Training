package JavaCollectionsPraticeQuestions.SecondRedoQ1;


import java.util.*;
public class ProductMain
{


    public static void main (String[] args)
    {


        List<Product> productList1 = new ArrayList<>();

        Product p1 = new Product(" Soccer Shoes" , "Footwear" , 800.00);
        Product p2 = new Product("Iphone" , "Electronics" , 10200.00);
        Product p3 = new Product("Jeans" , "Clothing" , 600.00);
        Product p4 = new Product("Socks" , "Footwear" , 70.00);
        Product p5 = new Product("Books" , "Stationery" , 800.00);

        // 1. Inserts 5 custom objects into an arraylist with class name product .


        productList1.add(p1);
        productList1.add(p2);
        productList1.add(p3);
        productList1.add(p4);
        productList1.add(p5);

//        for (Product product : productList1)
//        {
//            System.out.println(product);
//        }
//
//        System.out.println("Size of the array list is now " + productList1.size());
//
//        System.out.println("---------------------------------------------------------------------------");
// 3. Print out a product where product description is a shoe

//        for ( Product product: productList1)
//        {
//            if (product.getName().toLowerCase().contains("Shoe"))
//            {
//                System.out.println(product);
//            }
//        }


        //5. If the size of the array is 5 then add another 2 elements to that arraylist.


        if (productList1.size() == 5)
        {

            productList1.add(new Product("PS5" , "Electronics" , 20321.00));
            productList1.add(new Product("XBOX" , "Electronics" , 22567.00));
        }


//        System.out.println(" Updated product list ");
//
//
//        for (Product product : productList1)
//        {
//            System.out.println(product);
//        }
//
//        System.out.println("Size of the array list is now " + productList1.size());
//
//        System.out.println("----------------------------");

        // 6. Do a search and get the object on the 3rd index and modify that value , insert it back on the third index.


//        // method (1)
//
//        Product get3rdIndexProduct = productList1.get(3);
//
//        //System.out.println(get3rdIndexProduct);
//
//        Product modifiedProduct = new Product("Sweaters" , "Clothing" , 650.00);
//
//        productList1.set(3 , modifiedProduct);
//
//
//        System.out.println(" Updated product list ");
//
//        for (Product product : productList1)
//        {
//            System.out.println(product);
//        }


        // Method (2)

//        Product get3rdIndexProduct = productList1.get(3);
//
//        get3rdIndexProduct.setName("Jacket");
//        get3rdIndexProduct.setCategory("Clothing");
//        get3rdIndexProduct.setPrice(2500.00);
//
//        System.out.println(" Updated product list ");
//
//        for (Product product : productList1)
//        {
//            System.out.println(product);
//        }

        // Create another arraylist


        List<Product> productList2 = new ArrayList<>();

        Product p9 = new Product("Sofas" , "Furniture" , 250.00);
        Product p10 = new Product("Rug" , "Furniture" , 1800.00);

        productList2.add(p9);
        productList2.add(p10);

//        for (Product product : productList2)
//        {
//            System.out.println(product);
//        }
//
        //  1.Create an arraylist of size 2 in which in the arraylist each object is an arraylist .


        List<List<Product>> prodlist = new ArrayList<>();

        prodlist.add(productList1);
        prodlist.add(productList2);

        // Iterate items

//        for (List<Product> products : prodlist)
//        {
//
//            System.out.println(products);
//        }


        // 2. Iterate the values of index 1 object .

//        List<Product> getProdList2 = prodlist.get(1);
//
//        for (Product products : getProdList2)
//        {
//
//            System.out.println(products);
//        }





    }
}
