package lld.strategy.vehicle;

import lld.strategy.driving.NormalDrivingStrategy;

public class PassengerVehicle extends Vehicle {

    public PassengerVehicle(){
        super(new NormalDrivingStrategy());
    }
}
