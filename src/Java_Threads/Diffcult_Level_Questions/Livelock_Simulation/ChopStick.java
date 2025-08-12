package Java_Threads.Diffcult_Level_Questions.Livelock_Simulation;

public class ChopStick
{
    private final String name;
    private boolean isTaken = false;


    public ChopStick(String name) {
        this.name = name;
    }


    public synchronized boolean pickup()
    {

        if (!isTaken)
        {
            isTaken = true;
            System.out.println(Thread.currentThread().getName() + " is successfully locked " + name);
            return true;
        }
        System.out.println(Thread.currentThread().getName() + " tried to pick " + name + " but it's already taken");
        return false;
    }

    public synchronized void putdown()
    {

        isTaken = false;
    }


    public String getName()
    {
     return name;
    }
}
