package org.example;

 // This is step 3 , which is about pre-trade risk checks. catch silly/unsafe orders before they hit the book).
// If risk says REJECT, we skip book.add(...) and log a reason.
public final class RiskEngine
{

 // --- very simple demo limits ---
 private static final long Max_Qty_Per_Order = 10 ; // // reject if qty > 10
 private static final long PRICE_UNITS = 2 ; // allow within ±2 units of mid price value

 static final class Result
 {

  final boolean ok;
  final String reason;
  Result(boolean ok , String reason)
  {
   this.ok = ok;
   this.reason = reason;
  }
  static Result ok()
  {

   return new Result(true , "ok");
  }
  static Result reject(String why)
  {

   return new Result(false , why);
  }

  public String toString()
  {

   if (ok == true)
   {
    return "OK";
   }
   else
   {
    return " REJECT : " + reason ;
   }

  };

 }

 Result check(Order o , long mid)
 {

  if (o.qty <= 0 )
  {

   return Result.reject("Qty<=0");

  }

  if (o.qty >  Max_Qty_Per_Order )
  {

   return Result.reject("maxQtyExceeded");

  }

  long upper = mid + PRICE_UNITS;
  long lower = Math.max(1 , mid - PRICE_UNITS);


  if (o.side == Side.BUY)
  {

   if (o.price > upper)
   {
    return Result.reject("buyPriceTooHigh");
   }
  }
  else // SELL
  {
   if (o.price < lower)
   {
    return Result.reject("sellPriceTooLow");

   }
  }

  return Result.ok();

 };




}
