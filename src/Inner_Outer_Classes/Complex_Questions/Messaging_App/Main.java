package Inner_Outer_Classes.Complex_Questions.Messaging_App;

import Generics_Pratice_Questions.Notification_System.Notification;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {

//        MessagingApp.ChatRoom  memberslist = null ;
//
//        memberslist.addMember("Alice");
//        memberslist.addMember("Bob");
//        memberslist.addMember("Charlie");

//        List<String> members = new ArrayList<>();
//        members.add("Alice");
//        members.add("Bob");
//        members.add("Charlie");

        MessagingApp messagingApp = new MessagingApp();
        MessagingApp.ChatRoom chatRoom = messagingApp.new ChatRoom("Room1" , new ArrayList<>());
        chatRoom.addMember("Alice");
        chatRoom.addMember("Bob");
        chatRoom.addMember("Charlie");
        chatRoom.sendMessage("Alice " , " Hello Everyone");





    }



}
