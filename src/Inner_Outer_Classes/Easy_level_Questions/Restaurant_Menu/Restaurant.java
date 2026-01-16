package Inner_Outer_Classes.Easy_level_Questions.Restaurant_Menu;

public class Restaurant
{
    public String restaurantName;


    public Restaurant(String restaurantName)
    {

        this.restaurantName = restaurantName;
    }

    public class Menu
    {

        public void display()
        {

            System.out.println("Welcome to " + restaurantName);
            System.out.println("Today's Menu");
            System.out.println("1. Pasta");
            System.out.println("2.Chicken Burger");

        }
    }
}
