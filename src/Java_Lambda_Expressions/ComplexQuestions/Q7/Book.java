package Java_Lambda_Expressions.ComplexQuestions.Q7;

public class Book
{

    public String title;
    public String author;
    public double price;

    public int copiesSold;

    public Book(String Title , String Author , double Price ,  int CopiesSold )
    {
        this.title = Title;
        this.author = Author;
        this.price = Price;
        this.copiesSold = CopiesSold;

    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getCopiesSold() {
        return copiesSold;
    }

    @Override
    public String toString() {
        return " Title : " + title + " Author : " + author + " Price : " + price + " Copies Sold : " + copiesSold ;
    }
}
