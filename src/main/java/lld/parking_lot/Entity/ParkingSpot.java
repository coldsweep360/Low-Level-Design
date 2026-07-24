package lld.parking_lot.Entity;

import lld.parking_lot.enums.VehicleType;

import java.util.Objects;

/**
 * A single bay in the simple model. The spot owns its occupancy invariant;
 * callers cannot set an arbitrary occupied flag.
 */
public final class ParkingSpot {
    private final String id;
    private final VehicleType acceptedType;
    private Vehicle parkedVehicle;

    public ParkingSpot(String id, VehicleType acceptedType) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.acceptedType = Objects.requireNonNull(acceptedType, "acceptedType must not be null");
    }

    /** Atomically parks a compatible vehicle for this deliberately small synchronous model. */
    public synchronized boolean park(Vehicle vehicle) {
        Objects.requireNonNull(vehicle, "vehicle must not be null");
        if (parkedVehicle != null || vehicle.getType() != acceptedType) {
            return false;
        }
        parkedVehicle = vehicle;
        return true;
    }

    /** Releases the exact vehicle occupying this bay. */
    public synchronized void leave(Vehicle vehicle) {
        if (parkedVehicle == null || !parkedVehicle.getRegistrationNumber()
                .equals(vehicle.getRegistrationNumber())) {
            throw new IllegalStateException("Vehicle is not parked in spot " + id);
        }
        parkedVehicle = null;
    }

    public String getId() { return id; }

    public VehicleType getAcceptedType() { return acceptedType; }

    public synchronized boolean isAvailable() { return parkedVehicle == null; }

    public synchronized Vehicle getParkedVehicle() { return parkedVehicle; }
}
