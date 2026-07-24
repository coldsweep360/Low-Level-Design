package lld.parking_lot2.model;

import lld.parking_lot2.enums.VehicleType;

public final class Bike extends Vehicle {
    public Bike(String registration) { super(registration, VehicleType.BIKE); }
}
