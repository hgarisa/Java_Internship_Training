package Java_String_Class_Methods.Q4;

/*

 Invoice Number Generator

     Problem Statement:
You are building an invoice number generator for a billing system.

The invoice number format should be:
<PREFIX>-<YEAR>-<MONTH>-<RANDOM 5 LETTERS>


Input:
Prefix (e.g., INV)

Output:
Invoice Number (e.g., INV-2025-06-ABXDK)


The random part should be 5 uppercase letters (A-Z).

     */

import java.util.*;
import java.time.LocalDate;
import java.util.Random;
public class InvoiceGenerator
{

    private String prefix;

    public InvoiceGenerator(String prefix) {
        this.prefix = prefix.toUpperCase();
    }

    public String generateInvoiceNumber() {
        LocalDate today = LocalDate.now();
        String year = String.valueOf(today.getYear());
        String month = String.format("%02d", today.getMonthValue());
        String randomPart = generateRandomLetters(5);
        return String.format("%s-%s-%s-%s", prefix, year, month, randomPart);
    }

    private String generateRandomLetters(int length) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(letters.charAt(random.nextInt(letters.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Flow: Create InvoiceGenerator -> Generate Invoice
        InvoiceGenerator invoice = new InvoiceGenerator("inv");
        System.out.println("Generated Invoice: " + invoice.generateInvoiceNumber());
    }


}
