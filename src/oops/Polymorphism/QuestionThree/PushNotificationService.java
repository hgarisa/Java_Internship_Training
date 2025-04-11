package oops.Polymorphism.QuestionThree;

// Implementation of push-notification message sending
class PushNotificationService implements MessageService {
    @Override
    public void sendMessage(String to, String message) {
        System.out.println("Sending PUSH notification to " + to + ": " + message);
    }
}