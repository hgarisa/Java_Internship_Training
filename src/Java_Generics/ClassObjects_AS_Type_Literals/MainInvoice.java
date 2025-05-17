package Java_Generics.ClassObjects_AS_Type_Literals;

import  java.util.*;
public class MainInvoice
{


    public static void main(String[] args) {
        try {
            // Use generic factory to create an Invoice instance
            Invoice invoice = ObjectFactory.getInstance(Invoice.class);

            // Output the created Invoice object
            System.out.println("Created Invoice: " + invoice);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
