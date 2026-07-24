package lld.parking_lot.pricing;

import lld.parking_lot.Ticket;

import java.time.LocalDateTime;

/** Flat-fee policy useful for the smallest parking-lot example. */
public final class FixedPricingStrategy implements PricingStrategy {
    private final double fee;

    public FixedPricingStrategy() { this(100.0); }
    public FixedPricingStrategy(double fee) {
        if (fee < 0) throw new IllegalArgumentException("fee cannot be negative");
        this.fee = fee;
    }
    @Override public double calculate(Ticket ticket, LocalDateTime exitTime) { return fee; }
}
