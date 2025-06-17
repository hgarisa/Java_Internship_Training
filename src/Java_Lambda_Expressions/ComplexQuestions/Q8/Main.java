package Java_Lambda_Expressions.ComplexQuestions.Q8;

import java.util.*;
import java.util.stream.Collectors;

/*
Scenario:
You analyze transactions. Each Transaction has:
transactionId, customerId, amount, type (PURCHASE, REFUND), timestamp
 Exercise:
Filter all REFUND transactions.
Group them by customerId.
For each customer, print: CustomerID -> List of refund amounts

 */
public class Main
{
    public static void main(String[] args)
    {

List<Transaction> transactions = new ArrayList<>();

        Transaction transaction1 = new Transaction(102, 33 , "PURCHASE" , 283.00);
        Transaction transaction2 = new Transaction(103, 34 , "REFUND" , 990.00);
        Transaction transaction3 = new Transaction(104, 35 , "REFUND" , 1250.99);
        Transaction transaction4 = new Transaction(105, 36 , "REFUND" , 7564.88);
        Transaction transaction5 = new Transaction(106, 37 , "PURCHASE" , 4529.44);

        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        transactions.add(transaction5);

transactions.stream().filter(transaction -> transaction.getType().equals("REFUND"))
        .collect(Collectors.groupingBy(Transaction::getCustomerID  , Collectors.mapping(Transaction::getAmount , Collectors.toList())))
        .forEach((customerID , refundList) -> System.out.println(" Customer ID : " + customerID + " Refunds Amount : " + refundList) );





}



}
