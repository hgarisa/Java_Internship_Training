package Inner_Outer_Classes.Easy_level_Questions.Button_Click_Listener;
/*
Problem Recap:
You are simulating a UI Button with a click listener interface.
When the button is clicked, it should perform a specific action.

         Task:
        Create a class Button with:

        A method setOnClickListener(OnClickListener listener)

        A method click() which calls listener.onClick()

        Define an interface OnClickListener with a method onClick()

        In your main() method:

        Create a Button object.

        Call setOnClickListener() using an anonymous inner class that prints:
        Button was clicked!

        Call click() to simulate the button being pressed.

*/


public class Main
{
    public static void main(String[]args)
    {

        Button button =  new Button();

        // Anonymous Inner Class
      button.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick() {
              System.out.println("Button Was Clicked");
          }
      });



      button.click(); // Simulate a click

    }

}
