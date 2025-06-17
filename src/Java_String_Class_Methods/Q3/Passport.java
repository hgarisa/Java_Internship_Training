package Java_String_Class_Methods.Q3;

/*

Passport Number Validator

Problem Statement:

Build a system that validates passport numbers:

Must start with 2 uppercase letters.

Followed by exactly 7 digits.

Total length must be 9.

 */

import java.util.*;
public class Passport
{


    private String passportNumber;

    public Passport(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public boolean isValid() {
        return passportNumber.matches("^[A-Z]{2}\\d{7}$");
    }

    public static void main(String[] args) {
        // Flow: Create Passport -> Validate Number
        Passport passport = new Passport("AB1234567");
        System.out.println("Is valid passport: " + passport.isValid());
    }


}
