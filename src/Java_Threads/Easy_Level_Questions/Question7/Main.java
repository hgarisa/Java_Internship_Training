package Java_Threads.Easy_Level_Questions.Question7;

public class Main
{
    public static void main(String[] args) throws InterruptedException {


        MovieTheatre theatre = new MovieTheatre();
        Thread user1 = new Thread(new Person(theatre , "Gio" , 1));
        Thread user2 = new Thread(new Person(theatre , "Mason" , 2));
        Thread user3 = new Thread(new Person(theatre , "Harry" , 3));
        Thread user4 = new Thread(new Person(theatre , "Leo" , 4));
        Thread user5 = new Thread(new Person(theatre , "Ronaldo" , 5));

        user1.start();
        user2.start();
        user3.start();
        user4.start();
        user5.start();

        user1.join();
        user2.join();
        user3.join();
        user4.join();
        user5.join();


        System.out.println("Remaining available seat is " + theatre.availableSeats);

    }
}
