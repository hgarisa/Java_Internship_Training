package oops.Polymorphism.QuestionThree;

public class TestPolymorphism3
{

    public static void main(String[] args) {
        NotificationManager manager = new NotificationManager();

        MessageService email = new EmailService();
        MessageService sms = new SMSService();
        MessageService push = new PushNotificationService();

        // Pass different implementations at runtime
        manager.notifyUser(email, "user@example.com", "Welcome!");
        manager.notifyUser(sms, "+1234567890", "Your OTP is 1234");
        manager.notifyUser(push, "userAppId123", "You've got a new badge!");
    }

}
