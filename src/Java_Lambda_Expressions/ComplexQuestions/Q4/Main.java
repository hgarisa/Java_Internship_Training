package Java_Lambda_Expressions.ComplexQuestions.Q4;

import java.util.*;

/*
A banking app stores transactions. Each Transaction has:
        Transaction ID
        Type (CREDIT, DEBIT)
        Amount
         Exercise:
        Sort all transactions such that:

        CREDIT transactions come before DEBIT

        Within each type, sort by amount in descending order

        Print the sorted list.
        */
public class Main
{
    public static void main(String[] args)
    {

List<Transaction> transactions = new ArrayList<>();

        Transaction t1 = new Transaction(12031 , "CREDIT" , 10212.933);
        Transaction t2 = new Transaction(10025 , "DEBIT" , 819231.00);
        Transaction t3 = new Transaction(10026 , "CREDIT" , 103728.22);
        Transaction t4 = new Transaction(10027 , "DEBIT" , 900012.01);
        Transaction t5 = new Transaction(10028 , "CREDIT" , 78323.99);
        Transaction t6 = new Transaction(10029 , "DEBIT" , 666538.57);

        transactions.add(t1);
        transactions.add(t2);
        transactions.add(t3);
        transactions.add(t4);
        transactions.add(t5);
        transactions.add(t6);

        transactions.stream().sorted((tA, tB) ->
        {
            // Sort CREDIT before DEBIT using Boolean.compare
            int typeOrder = Boolean.compare(tA.getType().equals("DEBIT"), tB.getType().equals("DEBIT"));

            if (typeOrder != 0) return typeOrder;

            // If same type, sort by amount descending

            return Double.compare(tB.getAmount(), tA.getAmount());

        }).forEach(System.out::println);




    }
}
