package Java_Lambda_Expressions.ComplexQuestions.Q9;

public class Movie
{
    public String title ;
    public String genre ;
    public double rating ;
    public int releaseYear;

    public Movie(String Title , String Genre , double Rating , int ReleaseYear)
    {
        this.title = Title;
        this.genre = Genre;
        this.rating = Rating;
        this.releaseYear = ReleaseYear;

    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    @Override
    public String toString() {
        return " Title : " + title + " Genre :" + genre + " Rating : " + rating + " Release year : " + releaseYear;
    }
}
