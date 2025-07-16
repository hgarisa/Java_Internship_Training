package Java_Date_Time.Timesheet_Validator;
import java.time.*;

/*
 * The `TimesheetValidator` main class simulates a real-world HR attendance system.
 * It validates whether an employee has fulfilled their 8-hour work requirement.
 */

public class TimesheetValidator
{
    public static void main(String[] args)
    {

        // Create an employee timesheet with:
        // - Name: John Doe
        // - Clock-in: 9:00 AM
        // - Clock-out: 5:30 PM
        // - Break: 45 minutes
        Employee employee = new Employee(
                "John Doe",
                LocalDateTime.of(2025, 7, 12, 9, 0),
                LocalDateTime.of(2025, 7, 12, 17, 30),
                Duration.ofMinutes(45)
        );

        // Required minimum work duration: 8 hours
        employee.validateWorkHours(Duration.ofHours(8));

    }

    /*
Program Flow Explanation:

Input:

Employee clock-in and clock-out times

Break time in minutes

Required working hours (usually 8h)

Processing:

Compute the duration between clock-in and clock-out.

Subtract break duration to get actual working time.

Compare actual time against the required threshold.

Output:

Display whether the employee meets the required work hours or not.

Provide a clear breakdown of clock-in, clock-out, break, and effective time worked.

     */

}
