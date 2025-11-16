package org.example;

import java.util.Objects;

// record keyword is a  built-in Java language feature since Java 16. It is used to  declare a special kind of class called a record class.
// Which is designed for creating immutable data carriers .
public  final class TopOfBook
{
    private final long tsNanos;
    private final long mid;
    private final Order bid;
    private final Order ask;

    public TopOfBook(long tsNanos, long mid, Order bid, Order ask) {
        this.tsNanos = tsNanos;
        this.mid = mid;
        this.bid = bid;
        this.ask = ask;
    }

    public long tsNanos() { return tsNanos; }
    public long mid()     { return mid; }
    public Order bid()    { return bid; }
    public Order ask()    { return ask; }

    // Equal method decides when two objects should be considered the same (logically), not just the same memory reference.

    public boolean equals(Object o)
    {

        if (this == o) return true;
        // If both variables point to the exact same object, they’re equal.
        if (!(o instanceof  TopOfBook that ))
            return false;

        // If o isn’t a TopOfBook, they can’t be equal → false.
        // If it is, Java binds a safely cast variable  called "that" for you.

        //  // 3) Field-by-field comparison

        // Compare each field that defines “sameness.”
        //  Objects.equals(a,b) handles nulls safely: returns true if both null;
        //

        return  tsNanos == that.tsNanos
                && mid == that.mid
                && Objects.equals(bid, that.bid)
                && Objects.equals(ask, that.ask);

    };

    public int hashCode()
    {

        return Objects.hash(tsNanos , mid , bid , ask);
        // This It returns a 32-bit int  of your object, computed from those fields.
        // Objects.hash(...) mixes the field values into one number (handling nulls safely).
        // The golden rule is that  If a.equals(b) is true, then a.hashCode() == b.hashCode() must also be true.
        // But the reverse is not the  same hash doesn’t guarantee equals.
        // Hash collections use this to bucket objects quickly. Later they use equals() to check exact equality.
    }

    public String toString() {
        return "TopOfBook{ts=" + tsNanos +
                ", mid=" + mid +
                ", bid=" + bid +
                ", ask=" + ask + "}";
    }

// What this class is that ? TopOfBook is just a tiny snapshot of the market right now:
    //  tsNanos – when we took the snapshot
    // mid – current mid price (your FEED’s value)
    //  bid – the best buy order (highest bid) from the book, or null
    // ask – the best sell order (lowest ask) from the book, or null

    // Think :  “what’s the best price to buy and the best price to sell at this instant?”

}
