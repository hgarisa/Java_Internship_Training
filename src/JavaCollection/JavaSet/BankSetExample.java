package JavaCollection.JavaSet;

import java.util.*;
public class BankSetExample
{
    public static void main(String[] args) {


        // Create bank accounts
        BankAccount acc1 = new BankAccount("111", "Alice");
        BankAccount acc2 = new BankAccount("222", "Bob");
        BankAccount acc3 = new BankAccount("333", "Charlie");
        BankAccount acc4 = new BankAccount("444", "Diana");

        // Initial set

        Set<BankAccount> accountsSet = new HashSet<>();

        accountsSet.add(acc1);
        accountsSet.add(acc2);
        accountsSet.add(acc3);
        accountsSet.add(acc4);

        // (1️) removeAll - Remove accounts present in another set

        Set<BankAccount> closedAccounts = new HashSet<>();
        closedAccounts.add(acc3);
        accountsSet.removeAll(closedAccounts);
        System.out.println("After removeAll (closed accounts): " + accountsSet);
       // System.out.println(closedAccounts);

        // (2️) retainAll - Retain only matching accounts


        accountsSet.add(acc3); // Re-add for testing
        Set<BankAccount> activeSubset = new HashSet<>();
        activeSubset.add(acc3);
        accountsSet.retainAll(activeSubset);
        System.out.println("After retainAll (only acc3 should remain): " + accountsSet);
        //System.out.println(activeSubset);


        // (3️) size() - Get number of accounts

        System.out.println("Number of accounts " + accountsSet.size());

        // (4️) isEmpty() - Check if set is empty
        boolean isEmpty = accountsSet.isEmpty();
        System.out.println("Is account set empty ? " + isEmpty);

        // Alternative empty check

        boolean isEmptyAlt = (accountsSet.size() == 0);
        System.out.println("Is account set empty ? (using size ) ?  " + isEmptyAlt);


        // (5️) contains() - Check if a specific account exists

//        boolean hasacc3 =  accountsSet.contains(new BankAccount("333", "Charlie"));
//        System.out.println("Does Set contain acc3 ? " + hasacc3);

        boolean hasacc3 =  accountsSet.contains(acc3);
        System.out.println("Does Set contain acc3 ? " + hasacc3);

       boolean hasAcc2 = accountsSet.contains(acc2);
        System.out.println("Does set contain acc2? " + hasAcc2);

        // (6️) null check

        accountsSet.add(null);
        System.out.println(" Contains null? " + accountsSet.contains(null));

        // (7️) Convert Set to List

        List<BankAccount> accountList = new ArrayList<>();
        accountList.addAll(accountsSet);
        System.out.println("Converted to List: " + accountList);

    }
}
