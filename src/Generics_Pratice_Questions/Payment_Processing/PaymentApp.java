package Generics_Pratice_Questions.Payment_Processing;

/*
  Payment Processing System with Generic Interfaces
Scenario:
You're creating a payment system where different payment processors
 (CreditCardProcessor, PaypalProcessor, etc.) must implement a generic PaymentProcessor<T extends Payment>
  interface.

  Task:
Create an interface:

public interface PaymentProcessor<T extends Payment> {
    void processPayment(T payment);
}

Implement this interface for multiple payment types like CreditCardPayment, PaypalPayment.

Write a generic class PaymentService<T> that uses the processor interface.


 */

import java.util.*;
public class PaymentApp
{
public static void main(String[] args)
{


    CreditCardPayment creditCardPayment1 = new CreditCardPayment(1000 , "123-2331-8319");
    PaypalPayment paypalPayment1 = new PaypalPayment(2000 , "userw@gmail.com");

    // Use payment Service with credit card

    PaymentService<CreditCardPayment> ccService = new PaymentService<>(new CreditCardProcessor());

ccService.makePayment(creditCardPayment1);

// Use payment service  with PayPal

    PaymentService<PaypalPayment> ppService = new PaymentService<>(new PaypalProcessor());
    ppService.makePayment(paypalPayment1);


}

}
