package Generics_Pratice_Questions.Report_Generator;
import java.util.*;

/*
Report Generator with Generic Method Constraints and Sorting
 Problem Statement:
You're building a reporting system for an organization. There are different types of reports:

UserReport

SalesReport

InventoryReport

All of them implement a common interface: Reportable.

Your task is to:

Create a generic method generateReport(List<T>) where T extends Reportable

Sort the list using a Comparator<? super T>

Overload the method to allow optional sorting

Goals:
Use bounded type parameters (<T extends Reportable>)

Use Comparator<? super T> to support flexible sorting

Demonstrate method overloading

Print the content of each report

* */
public class ReportApp
{
    public static void main(String[] args)
    {

  List<UserReport> myUserReports = List.of(
          new UserReport("Login Stats" , "Most Users log in at at 8am"),
          new UserReport("Profile Updates" , "20% of users updated their profiles")
          );

    List<SalesReport> mysalesreports = new ArrayList<>(List.of(

            new SalesReport("Q1" , 25000),
            new SalesReport("Q2" , 20000),
            new SalesReport("Q3", 32000)
    ));

        System.out.println("User reports with  (no sorting) : ");

        ReportUtils.generateReport(myUserReports);

        System.out.println("Sales report sorted by revenue");
        ReportUtils.generateReport
                (mysalesreports , Comparator.comparingDouble(sr -> ((SalesReport) sr).revenue).reversed());


    }
}
