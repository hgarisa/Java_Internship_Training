package Java_IO.Files.Resume_Parser;
import java.io.*;
import java.util.*;
/*
 * Reads resumes in plain text format from a folder and extracts key fields.
 */
public class ResumeParser {

    public List<ResumeEntry> parseResumes(String folderPath) throws IOException {
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new IllegalArgumentException("Invalid folder path");
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        List<ResumeEntry> entries = new ArrayList<>();

        for (File file : files) {
            String name = "", email = "", skills = "";

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Name:")) {
                        name = line.replace("Name:", "").trim();
                    } else if (line.startsWith("Email:")) {
                        email = line.replace("Email:", "").trim();
                    } else if (line.startsWith("Skills:")) {
                        skills = line.replace("Skills:", "").trim();
                    }
                }
            }

            if (!name.isEmpty() && !email.isEmpty()) {
                entries.add(new ResumeEntry(name, email, skills));
            }
        }

        return entries;
    }

    public void writeToCSV(List<ResumeEntry> entries, String outputCsvPath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputCsvPath))) {
            writer.println("Name,Email,Skills");
            for (ResumeEntry entry : entries) {
                writer.printf("\"%s\",\"%s\",\"%s\"%n",
                        entry.getName(),
                        entry.getEmail(),
                        entry.getSkills()
                );
            }
        }
    }
}

