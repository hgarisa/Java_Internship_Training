package JavaCollection.JavaSet;

public class Book
{

    private String title;
    private String author;
    private String isbn;


    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }


    // Getters

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }


    // Override equals and hashCode to make Set handle duplicates correctly

    public boolean equals(Object o )
    {
        if(this == o)
        {
           return  true ;
        }
        if (!(o instanceof Book))
        {
            return false;
        }

        Book book = (Book) o;
        return this.isbn.equals(book.isbn); // Uniqueness based on ISBN

    }

    public int hashCode()
    {

        return this.isbn.hashCode();
    }

    public String toString() {
        return "[" + title + " by " + author + ", ISBN: " + isbn + "]";
    }

}
