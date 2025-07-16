package Java_IO.Files.Resume_Parser;

/*
 * Represents a structured candidate resume entry parsed from a raw .txt file.
 */
public class ResumeEntry
{
    private String name;
    private String email;
    private String skills;

    public ResumeEntry(String name, String email, String skills) {
        this.name = name;
        this.email = email;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSkills() {
        return skills;
    }




}
