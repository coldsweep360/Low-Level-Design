package lld.parking_lot2.strategy.payment;

import lld.parking_lot2.model.Ticket;

public final class CashPayment implements PaymentStrategy {
    @Override public boolean process(Ticket ticket, double amount) {
        System.out.println("Cash accepted for " + ticket.getId() + ": " + amount);
        return true;
    }
}
