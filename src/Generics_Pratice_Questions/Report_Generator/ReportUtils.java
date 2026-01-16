package Generics_Pratice_Questions.Report_Generator;

import java.util.*;
public class ReportUtils
{
    // Basic Version : Just Prints reports

    public static <T extends Reportable> void generateReport(List<T> reports)
    {
        for (T report: reports)
        {
            System.out.println(report);
        }

    }

// Overloaded Version : Sort then print

    public static <T extends Reportable> void generateReport(List<T> reports ,  Comparator<? super T> comparator)
    {
        reports.sort(comparator);
        generateReport(reports); // reuse the basic method
    }


}
