package Java_Lambda_Expressions.ComplexQuestions.Q9;
import java.util.*;
import java.util.stream.Collectors;

/*

You work for a streaming service. Each Movie has:

title, genre, rating (0–10), releaseYear

 Exercise:
Group movies by genre
For each genre:
Count how many movies have a rating above 7
Print: Genre -> Highly Rated Movie Count
Bonus: Use Collectors.filtering() if using Java 9+

* */

public class Main
{
    public static void main(String[] args)
    {

        List<Movie> movies = new ArrayList<>();

        Movie movie1 = new Movie("Hit The Third Case" ,  "Action" , 7.5 , 2025);
        Movie movie2 = new Movie("Retro" ,  "Drama" , 7.4 , 2025);
        Movie movie3 = new Movie("Mission Impossible" ,  "Action" , 5.2 , 2020);
        Movie movie4 = new Movie("Ant Man and the Wasp" ,  "Action" , 8.4 , 2022);
        Movie movie5 = new Movie("Avengers : EndGame" ,  "Action" , 6.8 , 2023);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

      movies.stream().collect(Collectors.groupingBy(Movie::getGenre , Collectors.filtering( movie -> movie.getRating() > 7 , Collectors.counting())))
              .forEach((genre , TotalMoviesCount) -> System.out.println(" Genre => " + genre + " Highly Rated Movie Count " + TotalMoviesCount ));


    }

}
