package Java_IO.Files.Resume_Parser;
/*
 * Main driver class for parsing resumes and exporting to CSV.
 */
public class ResumeParserApp {
    public static void main(String[] args) {
        String resumesFolder = "resumes"; // Make sure this folder exists and has .txt resumes
        String csvOutput = "parsed_resumes.csv";

        ResumeParser parser = new ResumeParser();

        try {
            var parsedResumes = parser.parseResumes(resumesFolder);
            parser.writeToCSV(parsedResumes, csvOutput);
            System.out.println("Parsed resumes saved to " + csvOutput);
        } catch (Exception e) {
            System.err.println("Error during resume parsing: " + e.getMessage());
        }
    }
}
