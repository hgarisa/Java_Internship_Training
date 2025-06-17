package Java_Lambda_Expressions.ComplexQuestions.Q3;
import Java_Lambda_Expressions.ComplexQuestions.Q2.Product;
import java.util.*;
import java.util.stream.Collectors;

/*
You're analyzing a list of customer invoices. Each Invoice includes:
Invoice ID
Customer ID
Amount
Status (PAID or PENDING)

Exercise:
        Group all PENDING invoices by customerId.

        Calculate the total pending amount for each customer.

        Print a summary like: CustomerID -> TotalPendingAmount.*/
public class Main
{
    public static void main(String[] args)
    {
        List<Invoice> myinvoice = new ArrayList<>();

        Invoice i1 = new Invoice(129320, 12012, 50210.12, "PAID");
        Invoice i2 = new Invoice(129321, 73225, 40210.22, "PENDING");
        Invoice i3 = new Invoice(781923, 28432, 617421.313, "PENDING");
        Invoice i4 = new Invoice(131222, 19021, 59000.24, "PENDING");
        Invoice i5 = new Invoice(555555, 847129, 723933.42, "PAID");

         myinvoice.add(i1);
        myinvoice.add(i2);
        myinvoice.add(i3);
        myinvoice.add(i4);
        myinvoice.add(i5);

       myinvoice.stream().filter(invoice -> invoice.getStatus().equals("PENDING"))
               .collect(Collectors.groupingBy(Invoice::getCustomerID , Collectors.summingDouble(Invoice::getAmount)))
               .forEach((customerID , TotalPendingAmount) -> System.out.println(" Customer ID : " + customerID + " Total Pending Amount : " + TotalPendingAmount));







    }
}
