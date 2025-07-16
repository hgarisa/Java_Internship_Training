package Enums.Day_Scheduler;

public enum Day
{
    MONDAY(9), TUESDAY(9), WEDNESDAY(9), THURSDAY(9), FRIDAY(8),
    SATURDAY(4), SUNDAY(0);

    private final int workingHours;

    Day(int hours) {
        this.workingHours = hours;
    }

    public int getWorkingHours() {
        return workingHours;
    }


}
