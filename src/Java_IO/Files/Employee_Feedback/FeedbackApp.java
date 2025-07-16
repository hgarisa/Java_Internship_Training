package Java_IO.Files.Employee_Feedback;
import java.util.*;

/*
 Scenario: Employee Feedback Analyzer
You're building a utility to read multiple feedback files submitted by employees in plain .txt format.
Each file contains:
         Employee name
         Department
         Feedback text (can be multi-line)
*/

/*
 * Main driver class that parses feedback and writes summary.
 */
public class FeedbackApp {
    public static void main(String[] args) throws Exception {
        String folderPath = "feedbacks"; // folder at project root
        String outputFile = "feedback_summary.txt";

        FeedbackParser parser = new FeedbackParser();
        List<FeedbackEntry> entries = parser.parseFeedbacks(folderPath);

        FeedbackSummaryGenerator generator = new FeedbackSummaryGenerator();
        generator.writeSummary(entries, outputFile);
    }
}
