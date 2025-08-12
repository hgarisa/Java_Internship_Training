package Java_Threads.Easy_Level_Questions.Question15;

public class Counter
{
    int count = 0 ;

    public synchronized void increment()
    {
        count++;
    }
}
