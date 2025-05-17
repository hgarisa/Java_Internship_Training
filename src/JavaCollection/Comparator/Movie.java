package JavaCollection.Comparator;

public class Movie
{

    private String title;
    private int releaseYear;
    private double rating; // out of 10

    public Movie(String title, int releaseYear, double rating) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return " Title : " + title + " , Release year : " +  releaseYear +  " , Rating : "  + rating;
    }



}
