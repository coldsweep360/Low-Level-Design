package lld.strategy;

import lld.strategy.vehicle.PassengerVehicle;
import lld.strategy.vehicle.Vehicle;

public class Client {
    public static void main(String[] args) {
        Vehicle obj = new PassengerVehicle();
        obj.drive();
    }
}
