package Java_Functional_Interfaces.Q4;
import java.util.*;
import java.util.function.*;

//  Loan Approval System

//         Scenario:
//         A bank wants to automate loan approval based on
//         income, credit score, and loan amount using functional interfaces.
//
//         Task:
//         Create class LoanApplication with fields: applicantName, income, creditScore, requestedAmount.
//
//        Use:
//
//        Predicate<LoanApplication> to check eligibility (income > X, credit score > Y).
//
//        Function<LoanApplication, Double> to calculate the approved loan amount
//        (may not always be full requested amount).
//
//        Consumer<LoanApplication> to update status (Approved or Rejected).
//


public class LoanApprovalSystem {

    static class LoanApplication {
        String applicantName;
        double income;
        int creditScore;
        double requestedAmount;
        String status;

        public LoanApplication(String applicantName, double income, int creditScore, double requestedAmount) {
            this.applicantName = applicantName;
            this.income = income;
            this.creditScore = creditScore;
            this.requestedAmount = requestedAmount;
        }

        @Override
        public String toString() {
            return applicantName + " | Income: " + income + " | CreditScore: " + creditScore + " | Requested: " + requestedAmount + " | Status: " + status;
        }
    }

    public static void main(String[] args) {

        List<LoanApplication> applications = Arrays.asList(
                new LoanApplication("David", 90000, 750, 50000),
                new LoanApplication("Emma", 45000, 620, 40000),
                new LoanApplication("Lucas", 80000, 690, 30000)
        );

        Predicate<LoanApplication> isEligible = app -> app.income > 50000 && app.creditScore >= 700;

        Function<LoanApplication, Double> approvedAmount = app -> {
            if (app.creditScore >= 750) return app.requestedAmount;
            else return app.requestedAmount * 0.8;
        };

        Consumer<LoanApplication> processApplication = app -> {
            if (isEligible.test(app)) {
                double approved = approvedAmount.apply(app);
                app.requestedAmount = approved;
                app.status = "Approved";
            } else {
                app.status = "Rejected";
            }
        };

        applications.forEach(processApplication);
        applications.forEach(System.out::println);
    }


    // LoanApplication holds applicant details.
// Predicate evaluates whether applicant is eligible.
// Function calculates approved amount (e.g., 80% of request if conditions apply).
// Consumer updates application status accordingly.

}

