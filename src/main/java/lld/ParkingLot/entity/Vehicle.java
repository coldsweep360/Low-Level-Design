package lld.ParkingLot.entity;

import lld.ParkingLot.enums.VehicleType;

public class Vehicle {
    private String vehicleId;
    private VehicleType vehicleType;

    // FIX: Allows a vehicle to be created with the identity and type used by allocation.
    public Vehicle(String vehicleId, VehicleType vehicleType) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
