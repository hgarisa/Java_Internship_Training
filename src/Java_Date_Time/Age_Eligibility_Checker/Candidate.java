package Java_Date_Time.Age_Eligibility_Checker;
import java.time.*;

/*
 * The `Candidate` class models an applicant with a name and date of birth.
 * The class provides a method to determine if the applicant is within a
 * specified age range (e.g., 21 to 35 years) as of today.
 */
public class Candidate
{
    private String name;
    private LocalDate dateOfBirth;

    public Candidate(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

/*
 * Checks whether the candidate's current age falls within the given range.
 * 1. Calculates the age using `Period.between(...)`
 * 2. Compares it with the minimum and maximum age bounds
 * 3. Prints eligibility status with age
 *
 */
public void checkEligibility(int minAge, int maxAge) {
    // Step 1: Calculate current age using today's date
    Period agePeriod = Period.between(dateOfBirth, LocalDate.now());
    int years = agePeriod.getYears();

    // Step 2: Print age and check against boundaries
    System.out.println("Candidate: " + name);
    System.out.println("Date of Birth: " + dateOfBirth);
    System.out.println("Current Age: " + years + " years");

    // Step 3: Determine eligibility
    if (years >= minAge && years <= maxAge) {
        System.out.println("Candidate is eligible (age between " + minAge + " and " + maxAge + ").");
    } else {
        System.out.println("Not eligible. Must be between " + minAge + " and " + maxAge + " years old.");
    }
}


}
