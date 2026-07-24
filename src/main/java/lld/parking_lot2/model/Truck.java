package lld.parking_lot2.model;

import lld.parking_lot2.enums.VehicleType;

public final class Truck extends Vehicle {
    public Truck(String registration) { super(registration, VehicleType.TRUCK); }
}
