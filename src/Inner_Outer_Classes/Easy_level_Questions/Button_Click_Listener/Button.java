package Inner_Outer_Classes.Easy_level_Questions.Button_Click_Listener;

public class Button
{

    private OnClickListener listener;
    public void setOnClickListener(OnClickListener listener)
    {
      this.listener = listener;
    }


    public void click()
    {
        if (listener != null)
        {
            listener.onClick();// Call listener's method
        }

    }


}
