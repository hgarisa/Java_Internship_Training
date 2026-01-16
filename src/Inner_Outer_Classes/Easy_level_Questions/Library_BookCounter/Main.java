package Inner_Outer_Classes.Easy_level_Questions.Library_BookCounter;

/*

 Task:
Create a class called Library.

Add a method named dailyReport().

Inside that method, define a local inner class called BookCounter.

BookCounter should have a method countIssuedBooks() that prints:


Number of books issued today: <some number>


Create an object of BookCounter inside dailyReport() and call countIssuedBooks().

Call dailyReport() from the main() method.


* */

public class Main
{
    public static void main(String[]args)
    {

        Library library = new Library();
        library.dailyReport();
    }
}
