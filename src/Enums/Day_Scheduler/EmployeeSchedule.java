package Enums.Day_Scheduler;

public class EmployeeSchedule
{
    private String employeeName;

    public EmployeeSchedule(String employeeName) {
        this.employeeName = employeeName;
    }

    public void showSchedule(Day day) {
        System.out.println(employeeName + "'s schedule for " + day + ": " + day.getWorkingHours() + " hours.");
    }


}
