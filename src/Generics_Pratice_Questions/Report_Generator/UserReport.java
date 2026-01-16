package Generics_Pratice_Questions.Report_Generator;

import Generics_Pratice_Questions.E_Commerce_Recommendation.User;

public class UserReport implements Reportable
{

    public String title ;
    public String content;

    public UserReport(String title , String content)
    {

        this.title = title;
        this.content = content;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getContent() {
        return content;
    }

    public String toString()
    {
     return "User Report - " + title + " : " + content;
    }

}
