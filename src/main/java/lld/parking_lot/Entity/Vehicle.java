package lld.parking_lot.Entity;

import lld.parking_lot.enums.VehicleType;

import java.util.Objects;

/** Immutable description of a vehicle entering the small parking facility. */
public final class Vehicle {
    private final String registrationNumber;
    private final VehicleType type;

    public Vehicle(String registrationNumber, VehicleType type) {
        this.registrationNumber = requireText(registrationNumber, "registrationNumber");
        this.type = Objects.requireNonNull(type, "type must not be null");
    }

    public String getRegistrationNumber() { return registrationNumber; }

    public VehicleType getType() { return type; }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value;
    }
}
