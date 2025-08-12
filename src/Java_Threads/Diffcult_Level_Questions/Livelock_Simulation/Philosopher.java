package Java_Threads.Diffcult_Level_Questions.Livelock_Simulation;

public class Philosopher implements Runnable
{

    private final String name;
    private final ChopStick leftChopStick;
    private final ChopStick rightChopStick;

    public Philosopher(String name, ChopStick leftChopStick, ChopStick rightChopStick) {
        this.name = name;
        this.leftChopStick = leftChopStick;
        this.rightChopStick = rightChopStick;
    }


    @Override
    public void run()
    {
        while (true)
        {

            // Philosopher is thinking
            System.out.println(name + " is thinking ");
            try {
                Thread.sleep(1000); // Simulate thinking
            } catch (InterruptedException e) {
                System.out.println(name + "was interrupted while thinking ");
            }

            // Try to pick up left chopstick
            if (leftChopStick.pickup())
            {
                System.out.println(name + " picked up the left chopstick " + leftChopStick.getName());


                // Try to pick up the right chopstick
                if (rightChopStick.pickup())
                {
                    System.out.println(name + " picked up the right chopstick " + rightChopStick.getName());

                    // Eat
                    System.out.println(name +  " is eating... ");

                    try {
                        Thread.sleep(1000); // simulate eating
                    } catch (InterruptedException e) {
                        System.out.println(name + "was interrupted while eating");
                    }

                    // put down right and left chopstick after eating
                    rightChopStick.putdown();
                    leftChopStick.putdown();
                    System.out.println(name + " has put down both chopsticks ");

                }
                else
                {
                    // couldn't pick up the right chopstick , so you put down the left one and try again

                    System.out.println(name + " couldn't pick up the right chopstick : " + rightChopStick.getName());
                    leftChopStick.putdown();
                    System.out.println(name + " has put down the left chopstick : " + leftChopStick.getName());
                }

            }
            else
            {
                System.out.println( name + " couldn't pick up the left chopstick " + leftChopStick.getName());
            }


        }




    }
}
