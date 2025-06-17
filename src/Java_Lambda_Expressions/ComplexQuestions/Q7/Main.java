package Java_Lambda_Expressions.ComplexQuestions.Q7;

import java.util.*;
import java.util.stream.Collectors;

/*
Scenario:
        You manage a bookstore’s inventory. Each Book has:
        title, author, price, copiesSold
         Exercise:
        Group books by author.
        Sum total copiesSold per author.
        Find and print the author with the most total sales.
        Bonus: Use max() with Map.Entry.comparingByValue()

*/

public class Main
{
    public static void main (String[] args)
    {


        List<Book> books = new ArrayList<>();


        Book book1 = new Book("Winne the Pooh" , "James Cameron" , 451.22 , 32);
        Book book2 = new Book("Atomic Habits" , "James Cameron" , 500.00  , 120);
        Book book3 = new Book("Scared Games" , "Peter Quill" , 749.99 , 50);
        Book book4 = new Book("Ghost Riders" , "Peter Quill" , 400.00 , 70);
        Book book5 = new Book("Squid Games" , "Charles Lee" , 700.00 , 100);
        Book book6 = new Book("Easter Hunt" , "Charles Lee" , 451.22 , 88);

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book5);
        books.add(book6);


        books.stream().collect(Collectors.groupingBy(Book::getAuthor , Collectors.summingInt(Book::getCopiesSold)))
                .entrySet().stream().max(Map.Entry.comparingByValue()).ifPresent(entry ->
                {

                    System.out.println("Author with the most copies sold is " + entry.getKey());
                    System.out.println("Total number of  copies sold are " + entry.getValue());

                });


    }
}
