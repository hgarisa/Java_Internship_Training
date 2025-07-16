package Java_Date_Time.Subscription_Renewal_Reminder;
import java.time.*;
/*
 * Main class simulates a real-world scenario where a user with a subscription
 * is checked for a renewal reminder 3 days before their plan ends.
 */
public class SubscriptionReminder
{
    public static void main(String[] args) {
        // Create a user whose subscription started on June 15, 2025
        // and lasts for 1 month (until July 15, 2025)
        User user = new User(
                "alice.smith",
                LocalDate.of(2025, 6, 15),
                Period.ofMonths(1)
        );

        // Check if a reminder needs to be sent today
        user.checkReminder();
    }
    /*

Program Flow Explanation:

Input:

Username

Subscription start date

Subscription period (e.g., 1 month)

Processing:

Calculate expiry date using subscriptionStart + subscriptionPeriod

Subtract 3 days from expiry to get reminderDate

Compare LocalDate.now() with reminderDate

Output:

If reminder date matches today, a renewal alert is printed.

Otherwise, it confirms no reminder is required today.

     */

}
