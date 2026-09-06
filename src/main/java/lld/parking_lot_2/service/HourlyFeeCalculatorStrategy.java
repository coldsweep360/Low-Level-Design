package lld.parking_lot_2.service;

import lld.parking_lot_2.enums.VehicleType;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Map;

public class HourlyFeeCalculatorStrategy implements FeeCalculator {
    private final Map<VehicleType, Integer> hourlyRates;
    // FIX: Rates are configured per vehicle type.
    public HourlyFeeCalculatorStrategy(Map<VehicleType, Integer> hourlyRates) {
        this.hourlyRates = new EnumMap<>(VehicleType.class);
        this.hourlyRates.putAll(hourlyRates);
    }
    public int calculate(LocalDateTime entryTime, LocalDateTime exitTime, VehicleType vehicleType) {
        // FIX: Round the full duration, preserving partial hours.
        double duration = Math.ceil((double) Duration.between(entryTime, exitTime).toMillis() / Duration.ofHours(1).toMillis());
        Integer rate = hourlyRates.get(vehicleType);
        if (rate == null) throw new IllegalArgumentException("No hourly rate configured for " + vehicleType);
        return (int) duration * rate;
    }
}
