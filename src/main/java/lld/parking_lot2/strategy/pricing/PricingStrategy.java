package lld.parking_lot2.strategy.pricing;

import lld.parking_lot2.model.Ticket;

import java.time.LocalDateTime;

public interface PricingStrategy {
    double calculate(Ticket ticket, LocalDateTime exitTime);
}
