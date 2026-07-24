package lld.parking_lot2.service;

import lld.parking_lot2.enums.PaymentMode;
import lld.parking_lot2.enums.PaymentStatus;
import lld.parking_lot2.factory.PaymentStrategyFactory;
import lld.parking_lot2.model.Ticket;

import java.util.Objects;

/** Coordinates gateway selection and records the payment outcome on a ticket. */
public final class PaymentProcessor {
    public boolean collect(Ticket ticket, double amount, PaymentMode mode) {
        Objects.requireNonNull(ticket);
        boolean accepted = PaymentStrategyFactory.create(mode).process(ticket, amount);
        ticket.markPayment(accepted ? PaymentStatus.SUCCESS : PaymentStatus.FAILED);
        return accepted;
    }
}
