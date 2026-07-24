package lld.parking_lot.parkinglot;

import lld.parking_lot.Entity.ParkingSpot;
import lld.parking_lot.Entity.Vehicle;

import java.util.List;
import java.util.Objects;

/** Coordinates a small fixed list of levels and does not own ticket lifecycle. */
public final class ParkingBuilding {
    private final List<ParkingLevel> levels;

    public ParkingBuilding(List<ParkingLevel> levels) {
        this.levels = List.copyOf(Objects.requireNonNull(levels));
    }

    public ParkingAssignment allocate(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            ParkingSpot spot = level.park(vehicle);
            if (spot != null) return new ParkingAssignment(level, spot);
        }
        throw new IllegalStateException("No spot available for " + vehicle.getType());
    }

    public List<ParkingLevel> getLevels() { return levels; }
}
