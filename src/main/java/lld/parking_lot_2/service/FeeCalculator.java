package lld.parking_lot_2.service;

import lld.parking_lot_2.enums.VehicleType;

import java.time.LocalDateTime;

public interface FeeCalculator {

    int calculate(LocalDateTime entryTime, LocalDateTime exitTime, VehicleType vehicleType);
}
