package Java_IO.Files.Employee_Feedback;
import java.io.*;
import java.nio.file.*;
import java.util.*;
/*
 * Reads all .txt files from a folder and parses them into FeedbackEntry objects.
 */
public class FeedbackParser {

    public List<FeedbackEntry> parseFeedbacks(String folderPath) throws IOException {
        List<FeedbackEntry> entries = new ArrayList<>();

        Files.list(Paths.get(folderPath))
                .filter(file -> file.toString().endsWith(".txt"))
                .forEach(path -> {
                    try {
                        List<String> lines = Files.readAllLines(path);
                        String name = "";
                        String dept = "";
                        StringBuilder feedbackBuilder = new StringBuilder();
                        boolean inFeedback = false;

                        for (String line : lines) {
                            if (line.startsWith("Name:")) {
                                name = line.substring(5).trim();
                            } else if (line.startsWith("Department:")) {
                                dept = line.substring(11).trim();
                            } else if (line.startsWith("Feedback:")) {
                                inFeedback = true;
                            } else if (inFeedback) {
                                feedbackBuilder.append(line).append(System.lineSeparator());
                            }
                        }
                        entries.add(new FeedbackEntry(name, dept, feedbackBuilder.toString().trim()));
                    } catch (IOException e) {
                        System.err.println("Failed to parse file: " + path.getFileName());
                    }
                });

        return entries;
    }
}

