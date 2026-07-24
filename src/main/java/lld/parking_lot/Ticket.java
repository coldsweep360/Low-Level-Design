package lld.parking_lot;

import lld.parking_lot.Entity.ParkingSpot;
import lld.parking_lot.Entity.Vehicle;
import lld.parking_lot.parkinglot.ParkingLevel;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/** Immutable parking receipt connecting a vehicle, level, spot, and entry time. */
public final class Ticket {
    private final String id = UUID.randomUUID().toString();
    private final Vehicle vehicle;
    private final ParkingLevel level;
    private final ParkingSpot spot;
    private final LocalDateTime entryTime;

    public Ticket(Vehicle vehicle, ParkingLevel level, ParkingSpot spot, LocalDateTime entryTime) {
        this.vehicle = Objects.requireNonNull(vehicle);
        this.level = Objects.requireNonNull(level);
        this.spot = Objects.requireNonNull(spot);
        this.entryTime = Objects.requireNonNull(entryTime);
    }

    public String getId() { return id; }
    public Vehicle getVehicle() { return vehicle; }
    public ParkingLevel getLevel() { return level; }
    public ParkingSpot getSpot() { return spot; }
    public LocalDateTime getEntryTime() { return entryTime; }
}
