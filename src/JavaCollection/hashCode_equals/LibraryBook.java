package JavaCollection.hashCode_equals;

import java.util.Objects;

public class LibraryBook
{

    private String isbn;
    private String title;
    private String author;

    public LibraryBook(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    // Getters
    public String getIsbn() {
        return isbn;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }

    // equals() override based on isbn
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryBook)) return false;

        LibraryBook other = (LibraryBook) o;
        return Objects.equals(this.isbn, other.isbn);
    }

    // hashCode() override based on isbn
    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    // Optional: toString for printing
    @Override
    public String toString() {
        return "LibraryBook{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }



}
