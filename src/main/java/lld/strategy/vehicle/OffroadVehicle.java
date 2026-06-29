package lld.strategy.vehicle;

import lld.strategy.driving.SportsDrivingStrategy;

public class OffroadVehicle extends Vehicle {

    public OffroadVehicle(){
        super(new SportsDrivingStrategy());
    }
}
