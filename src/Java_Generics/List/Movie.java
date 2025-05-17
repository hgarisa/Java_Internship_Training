package Java_Generics.List;

import java.util.*;
public class Movie
{

    private String title;
    private String genre;
    private int releaseYear;

    public Movie(String title, String genre, int releaseYear) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }


}
