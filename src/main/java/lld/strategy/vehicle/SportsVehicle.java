package lld.strategy.vehicle;

import lld.strategy.driving.SportsDrivingStrategy;

public class SportsVehicle extends Vehicle{

    public SportsVehicle(){
        super(new SportsDrivingStrategy());
    }

}
