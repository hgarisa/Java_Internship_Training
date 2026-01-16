package Inner_Outer_Classes.Easy_level_Questions.Laptop_Processor;

import java.util.*;
/*
Assignment: Laptop and Processor (Non-Static Inner Class)

        Scenario:

A laptop has a processor. Each laptop can have a different processor model. You need to show how a
 processor accesses information about its laptop.

  Task:

        Create an outer class Laptop with fields:

        brand (e.g., "Dell")

        price (e.g., 85000)

        Inside the Laptop class, create a non-static inner class Processor with a method
         showProcessorDetails() that prints:

        This is a processor of a <brand> laptop priced at <price> INR.

        In the main() method:

        Create an instance of Laptop.

        Use it to create a Processor instance.

        Call showProcessorDetails().

*/


public class Main
{
    public static void main(String[]args)
    {
        Laptop laptop = new Laptop("Dell" , 8500.00);
        Laptop.Processor processor = laptop.new Processor();
        processor.showProcessorDetails();
    }
}
