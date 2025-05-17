package JavaCollection.JavaMap;

public class LibraryBook
{


    private String title;
    private String author;
    private int yearPublished;

    public LibraryBook(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    // Getters

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearPublished() {
        return yearPublished;
    }


    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Year: " + yearPublished;
    }





}
