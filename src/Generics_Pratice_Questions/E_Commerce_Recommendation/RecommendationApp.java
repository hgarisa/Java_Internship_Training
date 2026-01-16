package Generics_Pratice_Questions.E_Commerce_Recommendation;
import java.util.*;
/*
E-Commerce Recommendation Engine with Generic Classes and Wildcards
         Problem Statement:
        You’re building a product recommendation system for an e-commerce platform. You have different product types like:

        Book

        Clothing

        Electronic

        Each recommendation engine should:

        Be specific to a product type

        Accept a list of available products and the current user

        Use sorting with Comparator<? super T> to sort recommendations (e.g., by price, rating, etc.)

         Goals:
        Create a generic class Recommender<T>

Accept a list of products and return filtered, sorted recommendations

        Use Comparator<? super T> to sort products

        Demonstrate usage with Book and Clothing
*/

public class RecommendationApp
{
    public static void main(String[] args)
    {


        User userone = new User("Hrudhay" , List.of("Fiction" , "T-Shirt"));
        List<Book> mybooks = List.of(
                new Book("The Alchemist" , 15.00 , "Fiction") ,
               new Book("Math 101 " , 10.00 , "Education") ,
                new Book("The Loop" , 12.00 , "Fiction")

        );

        List<Clothing> myclothes = List.of(

                new Clothing("Cool T-Shirt" , 30.00 , "T-Shirt") ,
                new Clothing("Winter Jacket" , 45.00 , "Jacket") ,
               new Clothing("Sidemen T-Shirt" , 50.00 , "T-Shirt")

                );

        Recommender<Book> bookRecommender = new Recommender<>();


        List<Book> myrecommendedbooks = bookRecommender.recommend(
                mybooks,
                userone ,
                Comparator.comparingDouble(Product::getPrice).reversed() // sort by price
        );

        System.out.println("Recommended Books : ");
        myrecommendedbooks.forEach(System.out::println);



        Recommender<Clothing> clothingRecommender = new Recommender<>();
        List<Clothing> myrecommendedclothing = clothingRecommender.recommend(
             myclothes ,
                userone ,
                Comparator.comparing(Product::getName).reversed() // sort by Name
        );

        System.out.println("Recommended Clothes : ");
        myrecommendedclothing.forEach(System.out::println);

    }
}
