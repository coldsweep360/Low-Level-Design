package lld.parking_lot.parkinglot;

import lld.parking_lot.Ticket;
import lld.parking_lot.payment.Payment;

/** Thin exit adapter that delegates the transactional exit workflow. */
public final class ExitGate {
    public double exit(ParkingLot lot, Ticket ticket, Payment payment) {
        return lot.exit(ticket, payment);
    }
}
