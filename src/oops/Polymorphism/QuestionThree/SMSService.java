package oops.Polymorphism.QuestionThree;

// Implementation of SMS-based message sending
class SMSService implements MessageService {
    @Override
    public void sendMessage(String to, String message) {
        System.out.println("Sending SMS to " + to + ": " + message);
    }
}