package Inner_Outer_Classes.Easy_level_Questions.Restaurant_Menu;

public class Main
{
    public static void main(String[] args)
    {

        Restaurant restaurant = new Restaurant("Taste of Punjab");
        Restaurant.Menu menu = restaurant.new Menu();
        menu.display();

    }
}
