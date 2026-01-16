package Inner_Outer_Classes.Complex_Questions.Bank_System;

import java.util.*;


public class Main
{
    public static void main(String[]args)
    {

        BankAccount mybankaccount = new BankAccount("Hrudhay Garisa" , 25000.00);
        mybankaccount.showAccountDetails();
        BankAccount.DebitCard mydebitcard = mybankaccount.new DebitCard();
        mydebitcard.withdraw(10000);
        mybankaccount.showAccountDetails();


        BankAccount.CreditCard mycredit = new BankAccount.CreditCard(7000);
        mycredit.useCredit(2000);

    }
}
