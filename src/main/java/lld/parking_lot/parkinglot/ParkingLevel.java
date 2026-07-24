package lld.parking_lot.parkinglot;

import lld.parking_lot.Entity.ParkingSpot;
import lld.parking_lot.Entity.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** One ordered level in the simple parking building. */
public final class ParkingLevel {
    private final int number;
    private final List<ParkingSpot> spots;

    public ParkingLevel(int number, List<ParkingSpot> spots) {
        if (number < 1) throw new IllegalArgumentException("level number must be positive");
        this.number = number;
        this.spots = List.copyOf(Objects.requireNonNull(spots));
    }

    /** Uses first-fit allocation, which is sufficient for the intentionally simple model. */
    public synchronized ParkingSpot park(Vehicle vehicle) {
        return spots.stream().filter(spot -> spot.park(vehicle)).findFirst().orElse(null);
    }

    public int getNumber() { return number; }
    public List<ParkingSpot> getSpots() { return new ArrayList<>(spots); }
}
