package Java_Date_Time.Subscription_Renewal_Reminder;
import java.time.*;

/*
 * The `User` class models a user with a subscription plan.
 * It helps determine if a renewal reminder should be sent today
 * based on the plan start date and duration.
 */
public class User
{
    private String username;
    private LocalDate subscriptionStart;
    private Period subscriptionPeriod;  // Duration of the subscription (e.g., 1 month)


    public User(String username, LocalDate subscriptionStart, Period subscriptionPeriod) {
        this.username = username;
        this.subscriptionStart = subscriptionStart;
        this.subscriptionPeriod = subscriptionPeriod;
    }

    /*
     * This method calculates:
     *  1. The expiry date of the subscription
     *  2. The reminder date (3 days before expiry)
     * Then checks if today's date matches the reminder date
     * and prints an appropriate message.
     */

    public void checkReminder() {
        // Step 1: Calculate expiry date from start date and subscription duration
        LocalDate expiry = subscriptionStart.plus(subscriptionPeriod);

        // Step 2: Subtract 3 days from expiry to determine reminder date
        LocalDate reminder = expiry.minusDays(3);

        // Step 3: Check today's date
        LocalDate today = LocalDate.now();

        System.out.println("User: " + username);
        System.out.println("Subscription Start: " + subscriptionStart);
        System.out.println("Expiry Date: " + expiry);
        System.out.println("Reminder Date: " + reminder);
        System.out.println("Today: " + today);

        // Step 4: If today is the reminder day, send reminder
        if (today.equals(reminder)) {
            System.out.println(" Reminder: Your subscription will expire on " + expiry + ". Please renew it now.");
        } else {
            System.out.println("No reminder needed today.");
        }
    }
}

