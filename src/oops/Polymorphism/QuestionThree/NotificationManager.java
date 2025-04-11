package oops.Polymorphism.QuestionThree;

// Class responsible for sending notifications
class NotificationManager {

    // Accepts any implementation of MessageService at runtime
    public void notifyUser(MessageService service, String user, String msg) {
        service.sendMessage(user, msg); // Dynamic dispatch to correct implementation
    }
}
