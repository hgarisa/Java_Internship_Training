package Inner_Outer_Classes.Complex_Questions.GUI_Event;

public class UIComponent
{
     String componentName;

    EventListener listener;
    public void setEventListener(EventListener listener)
    {

        this.listener = listener;
    }

    public void triggerEvent()
    {

        listener.onEvent(componentName);
    }

}
