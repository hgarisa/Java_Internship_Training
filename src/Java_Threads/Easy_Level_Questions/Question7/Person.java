package Java_Threads.Easy_Level_Questions.Question7;

public class Person implements Runnable
{

    public String personName;
    public int seatNumber;

    MovieTheatre theatre;

    public Person(MovieTheatre theatre , String personName , int seatNumber)
    {
        this.theatre = theatre;
        this.personName = personName;
        this.seatNumber = seatNumber;

    }
    public void run()
    {
        
       theatre.bookSeat(personName , seatNumber);
    }
}
