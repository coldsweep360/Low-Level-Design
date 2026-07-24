package lld.parking_lot2.factory;

import lld.parking_lot2.enums.VehicleType;
import lld.parking_lot2.model.Bike;
import lld.parking_lot2.model.Car;
import lld.parking_lot2.model.Truck;
import lld.parking_lot2.model.Vehicle;

import java.util.Objects;

public final class VehicleFactory {
    private VehicleFactory() { }
    public static Vehicle create(String registration, VehicleType type) {
        return switch (Objects.requireNonNull(type)) {
            case BIKE -> new Bike(registration);
            case CAR -> new Car(registration);
            case TRUCK -> new Truck(registration);
        };
    }
}
