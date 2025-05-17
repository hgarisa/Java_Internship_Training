package JavaCollection.JavaList;

public class Book implements Comparable<Book>
{
    public String title;
    public String author;

    // Constructor
    public Book (String title , String author )
    {
        this.title = title;
        this.author = author;

    }

    // Getter

//    public String getTitle()
//    {
//       return title;
//    }
//
//    public String getAuthor()
//    {
//        return author;
//    }

// toString() for easy printing

    public  String toString()
 {

     return title + " by " + author ;
 }

    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title); // natural order = title
    }




}
