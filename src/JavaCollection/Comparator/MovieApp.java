package JavaCollection.Comparator;

import java.util.*;
public class MovieApp
{
    public static void main(String[] args)
    {


        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Inception", 2010, 8.8));
        movies.add(new Movie("The Matrix", 1999, 8.7));
        movies.add(new Movie("Interstellar", 2014, 8.6));
        movies.add(new Movie("Tenet", 2020, 7.5));

        System.out.println("Original List:");
        movies.forEach(System.out::println);

        System.out.println("\nSorted by Rating (High to Low):");
        movies.sort(new RatingComparator());
        movies.forEach(System.out::println);

        System.out.println("\nSorted by Title:");
        movies.sort(new TitleComparator());
        movies.forEach(System.out::println);

        System.out.println("\nSorted by Release Year:");
        movies.sort(new ReleaseYearComparator());
        movies.forEach(System.out::println);




    }


}
