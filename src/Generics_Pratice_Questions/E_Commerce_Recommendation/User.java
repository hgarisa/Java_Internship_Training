package Generics_Pratice_Questions.E_Commerce_Recommendation;

import java.util.List;

public class User
{

    public String name ;
    public List<String> interests;

    public User(String name , List<String> interests)
    {
        this.name = name;
        this.interests = interests;
    }

    public List<String> getInterests()
    {
        return interests;
    }


}
