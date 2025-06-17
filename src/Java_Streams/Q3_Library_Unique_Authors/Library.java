package Java_Streams.Q3_Library_Unique_Authors;

import java.util.*;
public class Library
{

    private String libraryName;
    private List<Book> books;

    public Library(String libraryName, List<Book> books) {
        this.libraryName = libraryName;
        this.books = books;
    }

    public List<Book> getBooks() { return books; }


}
