package Java_Generics.List;

import java.util.*;
public class MovieApp
{

    public static void main(String[] args) {
        // Create a generic list of Movie objects
        List<Movie> movieList = new ArrayList<>();

        // Adding movies to the list
        movieList.add(new Movie("Inception", "Science Fiction", 2010));
        movieList.add(new Movie("The Godfather", "Crime", 1972));
        movieList.add(new Movie("Interstellar", "Science Fiction", 2014));

        // Accessing a movie using get()
        Movie firstMovie = movieList.get(0);
        System.out.println("First Movie: " + firstMovie.getTitle() + " (" + firstMovie.getReleaseYear() + ")");

        // Iterating with Iterator
        System.out.println("\n--- All Movies (using Iterator) ---");
        Iterator<Movie> iterator = movieList.iterator();
        while (iterator.hasNext()) {
            Movie movie = iterator.next();
            System.out.println(movie.getTitle() + " - " + movie.getGenre() + " (" + movie.getReleaseYear() + ")");
        }

        // Iterating with enhanced for-loop
        System.out.println("\n--- All Movies (using for-each loop) ---");
        for (Movie movie : movieList) {
            System.out.println(movie.getTitle() + " - " + movie.getGenre() + " (" + movie.getReleaseYear() + ")");
        }
    }



}
