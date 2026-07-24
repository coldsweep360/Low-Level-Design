package lld.parking_lot2.strategy.pricing;

import lld.parking_lot2.model.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;

/** Bills completed hours, rounding a partial hour upward. */
public final class TimeBasedPricing implements PricingStrategy {
    private final double hourlyRate;
    public TimeBasedPricing() { this(50.0); }
    public TimeBasedPricing(double hourlyRate) {
        if (hourlyRate < 0) throw new IllegalArgumentException("hourlyRate cannot be negative");
        this.hourlyRate = hourlyRate;
    }
    @Override public double calculate(Ticket ticket, LocalDateTime exitTime) {
        long minutes = Math.max(1, Duration.between(ticket.getEntryTime(), exitTime).toMinutes());
        return Math.ceil(minutes / 60.0) * hourlyRate;
    }
}
