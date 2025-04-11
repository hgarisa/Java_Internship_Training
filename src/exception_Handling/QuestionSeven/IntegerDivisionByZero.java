package exception_Handling.QuestionSeven;

// Custom exception extending Exception → checked exception
class IntegerDivisionByZero extends Exception { // (11)

    // Constructor that accepts an error message
    IntegerDivisionByZero(String str) {
        super(str); // Pass message to parent Exception constructor
    } // (12)
}
