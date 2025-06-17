package JavaCollection.JavaSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnyClass
{
    public static void main(String[] args)
    {


       Set<BankAccount> myset = new HashSet<>();

       // add elements (1)
        myset.add(new BankAccount("Z001" , "Hrudhay Garisa"));
        myset.add(new BankAccount("Z002" , " Sri krishna Garisa"));
        myset.add(new BankAccount("Z003" , " Rohini Raju Garisa"));
        myset.add(new BankAccount("Z004" , "Nathan Dawe"));



        for ( BankAccount set : myset)
        {

            System.out.println(set);
        }

        System.out.println("\n" + myset + "\n");

        // (2) Remove an element

       myset.remove(new BankAccount("Z001" , "Hrudhay Garisa"));


        //System.out.println(myset);

        //  Contains Value




    }
}
