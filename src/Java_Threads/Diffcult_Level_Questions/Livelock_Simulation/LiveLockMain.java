package Java_Threads.Diffcult_Level_Questions.Livelock_Simulation;

public class LiveLockMain
{
    public static void main(String[] args)
    {

        // Create two chopSticks

        ChopStick chopStick1 = new ChopStick("Chopstick-1");
        ChopStick chopStick2 = new ChopStick("Chopstick-2");

        // Create two philosophers sharing the chopsticks (reversed order)

        Philosopher philosopher1 = new Philosopher("Philosopher-1" , chopStick1 , chopStick2);
        Philosopher philosopher2 = new Philosopher("Philosopher-2" , chopStick2 , chopStick1);

        // Start the threads

        Thread thread1 = new Thread(philosopher1);
        Thread thread2 = new Thread(philosopher2);

        thread1.start();
        thread2.start();

    }
}
