package Java_Date_Time.Timesheet_Validator;
import java.time.*;
/*
 * The `Employee` class represents an employee's daily attendance.
 * It contains:
 *  - Clock-in time
 *  - Clock-out time
 *  - Break duration
 *
 * The core functionality is to validate whether the employee has worked
 * the required minimum number of hours after subtracting break time.
 */
public class Employee
{
    private String name;
    private LocalDateTime clockIn;
    private LocalDateTime clockOut;
    private Duration breakDuration;


    public Employee(String name, LocalDateTime clockIn, LocalDateTime clockOut, Duration breakDuration) {
        this.name = name;
        this.clockIn = clockIn;
        this.clockOut = clockOut;
        this.breakDuration = breakDuration;
    }
    /*
     * Validates if the employee has worked the required number of hours
     * by computing:
     *  1. Total time between clock-in and clock-out
     *  2. Subtracting the break time
     *  3. Comparing the result against the required duration
     *
     * @param requiredHours Minimum required working duration
     */

    public void validateWorkHours(Duration requiredHours) {
        // Step 1: Calculate total hours between clock-in and clock-out
        Duration totalWorked = Duration.between(clockIn, clockOut);

        // Step 2: Subtract break duration to get net worked hours
        Duration netWorked = totalWorked.minus(breakDuration);

        System.out.println("Employee: " + name);
        System.out.println("Clock-In: " + clockIn);
        System.out.println("Clock-Out: " + clockOut);
        System.out.println("Break Time: " + breakDuration.toMinutes() + " minutes");

        // Step 3: Compare net worked duration with required duration
        if (netWorked.compareTo(requiredHours) >= 0) {
            System.out.println(" Minimum working hours met.");
            System.out.println("Total Effective Hours: " +
                    netWorked.toHoursPart() + "h " + netWorked.toMinutesPart() + "m");
        } else {
            System.out.println(" Insufficient working hours.");
            System.out.println("Only Worked: " +
                    netWorked.toHoursPart() + "h " + netWorked.toMinutesPart() + "m");
        }
    }




}


