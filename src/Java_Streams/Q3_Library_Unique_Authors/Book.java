package Java_Streams.Q3_Library_Unique_Authors;

import java.util.*;
public class Book
{
    private String isbn;
    private String title;
    private List<String> authors;

    public Book(String isbn, String title, List<String> authors) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
    }

    public List<String> getAuthors() { return authors; }
}
