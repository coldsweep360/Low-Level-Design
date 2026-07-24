package lld.parking_lot2.model;

import lld.parking_lot2.enums.VehicleType;

import java.util.Objects;

/** Immutable vehicle identity used by tickets and parking spots. */
public abstract class Vehicle {
    private final String registration;
    private final VehicleType type;

    protected Vehicle(String registration, VehicleType type) {
        if (registration == null || registration.isBlank()) throw new IllegalArgumentException("registration required");
        this.registration = registration;
        this.type = Objects.requireNonNull(type);
    }
    public final String getRegistration() { return registration; }
    public final VehicleType getType() { return type; }
}
