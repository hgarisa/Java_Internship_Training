package Java_IO.Files.Employee_Feedback;


/*
 * Represents a single employee feedback entry.
 */


public class FeedbackEntry {
    private String name;
    private String department;
    private String feedback;

    public FeedbackEntry(String name, String department, String feedback) {
        this.name = name;
        this.department = department;
        this.feedback = feedback;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getFeedback() {
        return feedback;
    }
}
