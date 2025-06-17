package Java_Streams.Q3_Library_Unique_Authors;

import java.util.*;
import java.util.stream.*;


public class AuthorExtractor
{
    public static List<String> getUniqueSortedAuthors(List<Library> libraries) {
        return libraries.stream()
                .flatMap(library -> library.getBooks().stream()) // flatten books from libraries
                .flatMap(book -> book.getAuthors().stream()) // flatten authors from books
                .distinct() // remove duplicates
                .sorted() // sort alphabetically
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Sample data
        List<Library> libraries = List.of(
                new Library("Central", List.of(
                        new Book("B1", "Java Basics", List.of("Author A", "Author B")),
                        new Book("B2", "Advanced Java", List.of("Author C", "Author B"))
                )),
                new Library("City", List.of(
                        new Book("B3", "Spring Framework", List.of("Author D", "Author A"))
                ))
        );

        // Extract authors
        List<String> authors = getUniqueSortedAuthors(libraries);

        // Print result
        authors.forEach(System.out::println);
    }

}
