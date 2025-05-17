package JavaCollection.JavaList;

import java.util.*;
public class BookListExample
{

    public static void main(String[] args)
    {

       // Create Book objects

//        Book book1 = new Book("1984", "George Orwell");
//        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee");
//        Book book3 = new Book("The Hobbit", "J.R.R. Tolkien");
//        Book book4 = new Book("The Great Gatsby", "F. Scott Fitzgerald");
//        Book book5 = new Book("Moby-Dick", "Herman Melville");
//
//        // Main list of books
//
//        List<Book> library = new ArrayList<>();
//
//        library.add(book1);
//        library.add(book2);
//        library.add(book3);
//        library.add(book4);
//
//
//        // Reading list (must contain same object references)
//
//        List<Book> readingList = new ArrayList<>();
//        readingList.add(book2); // same object as in library
//        readingList.add(book3); // same object as in library
//        readingList.add(book5); // different object
//
//        // Keep only books in both library and readingList
//
//        library.retainAll(readingList);
//
//        System.out.println("Books in both library and reading list:");
//
//        for ( Book book : library)
//        {
//
//            System.out.println(book);
//        }
//
//        // Size of resulting list
//
//        System.out.println("\n Total books Retained :  " + library.size() );
//
//        // Sublist: get the first book only
//
//        if(!library.isEmpty())
//        {
//
//            List<Book> shortlist = library.subList(0,1);
//            System.out.println("\nShort list (first book):");
//            for ( Book bk : shortlist)
//            {
//                System.out.println(bk);
//            }
//        }



        List<Book> books = new ArrayList<>();

        books.add(new Book("Java Basics", "Alice"));
        books.add(new Book("Algorithms", "Bob"));
        books.add(new Book("Data Structures", "Charlie"));

        Collections.sort(books);
        books.forEach(System.out::println);


    }


}
