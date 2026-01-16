package Inner_Outer_Classes.Complex_Questions.Messaging_App;

import Inner_Outer_Classes.Complex_Questions.GUI_Event.Label;

import java.util.List;

public class MessagingApp
{

    class ChatRoom
    {

         String roomName;
         List<String> members;

         public ChatRoom(String roomName , List<String> members)
         {
             this.roomName = roomName;
             this.members = members;
         }
         public void addMember(String username)
         {

             members.add(username);

         }

        public void sendMessage(String sender , String senderMessage )
        {

            System.out.println(sender +  senderMessage);

           class NotificationDispatcher
            {
//                String toUser;
//                String outputMessage;
                Notifier notifier;

                public NotificationDispatcher()
                {

                }
                public void dispatch(Notifier notifier)
                {

                    notifier.notify("Bob", senderMessage);
                    notifier.notify("Charlie", senderMessage);

                }

            }
           NotificationDispatcher notificationDispatcher = new NotificationDispatcher();
           notificationDispatcher.dispatch(new Notifier() {
               @Override
               public void notify(String toUser, String message)
               {

                   System.out.println("[Notification] to " + toUser + " : "  + sender + "says : -> " + message);
               }
           });
            NotificationDispatcher notificationDispatcher2 = new NotificationDispatcher();

//            notificationDispatcher2.dispatch(new Notifier() {
//                @Override
//                public void notify(String toUser, String message)
//                {
//                    System.out.println("[Notification] to " + toUser + " : "  + sender + "says : -> " + message);
//
//                }
//            });


        }





    }

}
