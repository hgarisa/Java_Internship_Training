package Java_String_Class_Methods.Q2;
/*

Product Code Normalizer

        Problem Statement:
        You are building a system where product codes come from multiple vendors in inconsistent formats.

         Normalize them:

        Remove all spaces and dashes.
        Convert to uppercase.
        If length is < 10 characters, pad with X on the right to make it 10 characters.

        */

import java.util.*;
public class Product
{

    private String productCode;

    public Product(String productCode) {
        this.productCode = productCode;
    }

    public String normalizeCode() {
        // Remove spaces and dashes
        String cleaned = productCode.replaceAll("[\\s-]", "").toUpperCase();

        // Pad if necessary
        if (cleaned.length() < 10) {
            cleaned = String.format("%-" + 10 + "s", cleaned).replace(' ', 'X');
        }
        return cleaned;
    }

    public static void main(String[] args) {
        // Flow: Create Product -> Normalize Code
        Product product = new Product(" ab-12-34  ");
        System.out.println("Normalized Code: " + product.normalizeCode());
    }
}
