package lld.strategy.vehicle;

import lld.strategy.driving.DrivingStrategy;

public class Vehicle {
    DrivingStrategy strategy;

    Vehicle(DrivingStrategy strategy){
        this.strategy = strategy;
    }

    public void drive(){
        strategy.drive();
    }

}
