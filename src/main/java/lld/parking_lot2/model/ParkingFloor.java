package lld.parking_lot2.model;

import lld.parking_lot2.enums.VehicleType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/** Owns a floor's inventory and performs deterministic first-compatible allocation. */
public final class ParkingFloor {
    private final String id;
    private final List<ParkingSpot> spots;

    public ParkingFloor(String id, List<ParkingSpot> spots) {
        this.id = Objects.requireNonNull(id);
        List<ParkingSpot> ordered = new ArrayList<>(Objects.requireNonNull(spots));
        ordered.sort(Comparator.comparing(ParkingSpot::getId));
        this.spots = List.copyOf(ordered);
    }

    public ParkingSpot reserve(Vehicle vehicle) {
        for (ParkingSpot spot : spots) if (spot.tryReserve(vehicle)) return spot;
        return null;
    }

    public long freeCount(VehicleType type) {
        return spots.stream().filter(s -> s.getAcceptedType() == type && !s.isOccupied()).count();
    }

    public String getId() { return id; }
    public List<ParkingSpot> getSpots() { return spots; }
}
