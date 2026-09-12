package lld.ParkingLot.service;

import lld.ParkingLot.enums.VehicleType;

import java.time.LocalDateTime;

public interface FeeCalculator {

    int calculate(LocalDateTime entryTime, LocalDateTime exitTime, VehicleType vehicleType);
}
