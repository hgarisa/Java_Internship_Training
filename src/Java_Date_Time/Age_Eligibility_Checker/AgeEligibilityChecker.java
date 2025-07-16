package Java_Date_Time.Age_Eligibility_Checker;
import java.time.*;
/*
 The `AgeEligibilityChecker` simulates a job application scenario
 where an organization checks the age of a candidate before proceeding.

 */
public class AgeEligibilityChecker
{
    public static void main(String[] args) {
        // Example candidate with date of birth: March 10, 1995
        Candidate candidate = new Candidate("Rahul Sharma", LocalDate.of(1995, 3, 10));

        // Job requires candidate to be between 21 and 35 years
        candidate.checkEligibility(21, 35);
    }

    /*
Program Flow Explanation:

Input:

Candidate’s name

Candidate’s date of birth

Required age range (e.g., 21–35 years)

Processing:

Calculate current age using Period.between(dob, today)

Compare the age with the allowed range

Print status with an age breakdown

Output:

Whether the candidate is eligible or not, along with age and message

     */

}
