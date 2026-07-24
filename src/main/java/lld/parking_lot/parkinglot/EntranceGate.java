package lld.parking_lot.parkinglot;

import lld.parking_lot.Entity.Vehicle;
import lld.parking_lot.Ticket;

/** Thin entry adapter; all state remains owned by the injected parking lot. */
public final class EntranceGate {
    public Ticket enter(ParkingLot lot, Vehicle vehicle) { return lot.enter(vehicle); }
}
