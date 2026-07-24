package lld.parking_lot2.model;

import lld.parking_lot2.enums.VehicleType;

public final class Car extends Vehicle {
    public Car(String registration) { super(registration, VehicleType.CAR); }
}
