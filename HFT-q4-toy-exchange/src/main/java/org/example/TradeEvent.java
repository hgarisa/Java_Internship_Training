package org.example;

import java.util.Objects;


// TradeEvent is a tiny message that says: A trade happened at time tsNanos and here is the Trade
// It’s just a wrapper: (timestamp, trade).

// Why do we need it ?
// In Step 4 we built a pub/sub market-data bus. The Exchange thread should publish typed events that subscribers can consume.
// Separating the engine (matching) from distribution (market data) keeps things clean and decoupled.
// Using a dedicated class (instead of passing a raw Trade) lets you attach metadata (the event time) and extend later (e.g., venue, sequence number) without changing the rest of the system.

// Notes
// 1. The class is final and fields are final → the event object itself can’t be modified after creation (good for messaging).
//2. The Trade inside is also immutable ( all its fields final), so the whole message is safely immutable.



public final  class TradeEvent
{

    private final long tsNanos;
    private final Trade trade;


    public TradeEvent(long tsNanos, Trade trade) {
        this.tsNanos = tsNanos;
        this.trade = trade;
    }

    public long tsNanos() { return tsNanos; }
    public Trade trade()  { return trade; }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TradeEvent that)) return false;
        return tsNanos == that.tsNanos &&
                Objects.equals(trade, that.trade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tsNanos, trade);
    }

    @Override
    public String toString() {
        return "TradeEvent{tsNanos=" + tsNanos + ", trade=" + trade + "}";
    }

}
