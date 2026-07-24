package lld.parking_lot.pricing;

import lld.parking_lot.Ticket;

import java.time.LocalDateTime;

/** Fee policy abstraction kept independent of parking allocation. */
public interface PricingStrategy {
    double calculate(Ticket ticket, LocalDateTime exitTime);
}
