package JavaCollection.Comparator;

import java.util.*;

public class RatingComparator implements Comparator<Movie>
{
    public int compare(Movie m1, Movie m2) {
        return Double.compare(m2.getRating(), m1.getRating()); // Descending order
    }

}
