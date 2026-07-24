package lld.parking_lot2.strategy.payment;

import lld.parking_lot2.model.Ticket;

public interface PaymentStrategy {
    boolean process(Ticket ticket, double amount);
}
