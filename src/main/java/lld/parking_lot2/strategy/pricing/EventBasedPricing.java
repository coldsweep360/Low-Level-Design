package lld.parking_lot2.strategy.pricing;

import lld.parking_lot2.model.Ticket;

import java.time.LocalDateTime;

/** Applies a predictable event-day multiplier to the normal hourly policy. */
public final class EventBasedPricing implements PricingStrategy {
    private final PricingStrategy base;
    private final double multiplier;
    public EventBasedPricing() { this(new TimeBasedPricing(), 1.5); }
    public EventBasedPricing(PricingStrategy base, double multiplier) {
        if (multiplier < 0) throw new IllegalArgumentException("multiplier cannot be negative");
        this.base = base;
        this.multiplier = multiplier;
    }
    @Override public double calculate(Ticket ticket, LocalDateTime exitTime) {
        return base.calculate(ticket, exitTime) * multiplier;
    }
}
