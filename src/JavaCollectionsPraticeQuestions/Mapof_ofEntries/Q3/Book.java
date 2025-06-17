package JavaCollectionsPraticeQuestions.Mapof_ofEntries.Q3;

public class Book
{
    public  String title;
    private  String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return " Book Title : " + title + " by " + author;
    }
}
