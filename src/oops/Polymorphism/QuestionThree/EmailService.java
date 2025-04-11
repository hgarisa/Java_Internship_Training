package oops.Polymorphism.QuestionThree;

// Implementation of email-based message sending
class EmailService implements MessageService {
    @Override
    public void sendMessage(String to, String message) {
        System.out.println("Sending EMAIL to " + to + ": " + message);
    }



}