package Java_Streams.Q4_Parallel_Stream_Word_Count;

import java.util.*;
import java.util.stream.*;

public class DocumentProcessor
{

    public static long countWords(List<Document> documents, Set<String> stopWords) {
        return documents.parallelStream() // process documents in parallel
                .flatMap(doc -> Arrays.stream(doc.getContent().split("\\s+"))) // split words
                .map(String::toLowerCase) // normalize to lowercase
                .filter(word -> !stopWords.contains(word)) // remove stop words
                .count(); // count remaining words
    }

    public static void main(String[] args) {
        // Sample data
        List<Document> documents = List.of(
                new Document("D1", "Java Streams are powerful and efficient"),
                new Document("D2", "Streams can be parallel or sequential"),
                new Document("D3", "Java supports functional programming")
        );

        Set<String> stopWords = Set.of("are", "and", "be", "or", "can");

        // Run word counting
        long count = countWords(documents, stopWords);
        System.out.println("Total word count after removing stop words: " + count);
    }


}
