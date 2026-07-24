package lld.parking_lot2.model;

import lld.parking_lot2.enums.GateType;
import lld.parking_lot2.service.ParkingLot;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public final class EntryGate extends Gate {
    private final ParkingLot lot;
    public EntryGate(String id, ParkingLot lot) { super(id); this.lot = Objects.requireNonNull(lot); }
    @Override public GateType getType() { return GateType.ENTRY; }
    public Optional<Ticket> admit(Vehicle vehicle, LocalDateTime time) { return lot.parkVehicle(vehicle, time); }
}
