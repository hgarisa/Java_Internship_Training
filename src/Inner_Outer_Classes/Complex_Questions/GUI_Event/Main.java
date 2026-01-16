package Inner_Outer_Classes.Complex_Questions.GUI_Event;

public class Main
{
    public static void main(String[] args)
    {

     Button button = new Button();

     // Anonymous Inner Class

         button.setEventListener(new EventListener() {
             @Override
             public void onEvent(String eventName) {
                 System.out.println(" Button " + eventName + " was clicked ! ");
             }
         });
         button.triggerEvent();

         Label label = new Label();
         label.setEventListener(new EventListener() {
             @Override
             public void onEvent(String eventName) {
                 System.out.println(" Label " + eventName + " was tapped!");
             }
         });

         label.triggerEvent();


    }
}
