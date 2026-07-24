package lld.parking_lot.pricing;

import lld.parking_lot.Ticket;

import java.time.LocalDateTime;
import java.util.Objects;

/** Delegates fee calculation to a replaceable policy. */
public final class CostComputation {
    private final PricingStrategy strategy;
    public CostComputation(PricingStrategy strategy) { this.strategy = Objects.requireNonNull(strategy); }
    public double compute(Ticket ticket, LocalDateTime exitTime) { return strategy.calculate(ticket, exitTime); }
}
