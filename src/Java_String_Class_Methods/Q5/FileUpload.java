package Java_String_Class_Methods.Q5;

/*

Filename Sanitizer for File Upload System

Problem Statement:

When users upload files, sanitize filenames:

Remove all non-alphanumeric characters except underscores and dots.
Limit filename to maximum 50 characters.
Convert to lowercase.

*/

import java.util.*;
public class FileUpload
{
    private String fileName;

    public FileUpload(String fileName) {
        this.fileName = fileName;
    }

    public String sanitizeFileName() {
        // Allow alphanumeric, underscore, dot
        String cleaned = fileName.replaceAll("[^a-zA-Z0-9_.]", "").toLowerCase();

        // Limit to 50 characters
        if (cleaned.length() > 50) {
            cleaned = cleaned.substring(0, 50);
        }
        return cleaned;
    }

    public static void main(String[] args) {
        // Flow: Create FileUpload -> Sanitize Filename
        FileUpload upload = new FileUpload("Invoice:Containers_List.pdf");
        System.out.println("Sanitized Filename: " + upload.sanitizeFileName());
    }


}
