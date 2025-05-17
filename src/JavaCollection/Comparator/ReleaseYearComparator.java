package JavaCollection.Comparator;

import java.util.*;
public class ReleaseYearComparator implements Comparator<Movie>
{

    @Override
    public int compare(Movie m1, Movie m2) {
        return m1.getReleaseYear() - m2.getReleaseYear();
    }

}
