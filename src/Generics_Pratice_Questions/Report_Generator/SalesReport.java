package Generics_Pratice_Questions.Report_Generator;

public class SalesReport implements Reportable
{

public String title;
public double revenue ;

public SalesReport(String title , double revenue)
{
    this.title = title;
    this.revenue = revenue;
}

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getContent() {
        return "Revenue : $ " + revenue;
    }

    public String toString()
    {
      return " Sales Report - " + title + " : $ " + revenue ;
    }
}
