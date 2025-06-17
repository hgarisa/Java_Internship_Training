package JavaCollectionsPraticeQuestions.Mapof_ofEntries.Q3;

import java.util.Map;

public class LibraryCatalog
{
    public static void main(String[] args)
    {


        // Map.of()
        Map<String , Book> immuttableBookMap = Map.of(
                "01291894317" , new Book("Atomic Habits" , "Steve Karr"),
                "2689431" , new Book("AI in Medicine" , "Frank Mocha"),
                "1297034" , new Book("Youtube Book" , "William Gallio")

                );
        immuttableBookMap.forEach((isbn , book ) -> System.out.println("ISBN : " + isbn + " => Book" + book));
        System.out.println("------------------------------------------------------------------------------------------");


        //Map.ofEntries()

        Map<String , Book> bookMap2 = Map.ofEntries(

                Map.entry("102918292" , new Book("Winnie the Poo" , "Brad Halt") ),
                Map.entry("23894928" , new Book("KSI" , "JJ Olatunji") ),
                Map.entry("198039" , new Book("Paul Brothers" , "Logan and Jake Paul") )
                );

        bookMap2.forEach((isbn , book) -> System.out.println(" ISBN : " + isbn + "," + book));


    }
}
