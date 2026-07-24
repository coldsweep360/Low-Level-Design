package lld.parking_lot2.model;

import lld.parking_lot2.enums.GateType;
import lld.parking_lot2.enums.PaymentMode;
import lld.parking_lot2.service.ParkingLot;

import java.time.LocalDateTime;
import java.util.Objects;

public final class ExitGate extends Gate {
    private final ParkingLot lot;
    public ExitGate(String id, ParkingLot lot) { super(id); this.lot = Objects.requireNonNull(lot); }
    @Override public GateType getType() { return GateType.EXIT; }
    public double checkout(Ticket ticket, LocalDateTime time, PaymentMode mode) {
        return lot.checkout(ticket, time, mode);
    }
}
