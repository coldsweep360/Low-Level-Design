package lld.parking_lot2.model;

import lld.parking_lot2.enums.VehicleType;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A concurrent parking resource. CAS makes reservation a single winner even
 * when entry gates on different threads inspect the same floor.
 */
public final class ParkingSpot {
    private final String id;
    private final VehicleType acceptedType;
    private final AtomicReference<Vehicle> occupant = new AtomicReference<>();

    public ParkingSpot(String id, VehicleType acceptedType) {
        this.id = Objects.requireNonNull(id);
        this.acceptedType = Objects.requireNonNull(acceptedType);
    }

    public boolean tryReserve(Vehicle vehicle) {
        Objects.requireNonNull(vehicle);
        return vehicle.getType() == acceptedType && occupant.compareAndSet(null, vehicle);
    }

    public void release(String registration) {
        occupant.updateAndGet(current -> {
            if (current == null || !current.getRegistration().equals(registration)) {
                throw new IllegalStateException("Spot " + id + " is not held by " + registration);
            }
            return null;
        });
    }

    public String getId() { return id; }
    public VehicleType getAcceptedType() { return acceptedType; }
    public boolean isOccupied() { return occupant.get() != null; }
}
