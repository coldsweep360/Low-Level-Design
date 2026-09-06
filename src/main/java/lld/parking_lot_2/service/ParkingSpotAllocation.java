package lld.parking_lot_2.service;

import lld.parking_lot_2.entity.*;

import java.util.List;

public class ParkingSpotAllocation {
    private ParkingLot parkingLot;

    public ParkingSpotAllocation(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingSpot allocate(Vehicle vehicle, EntryGate entryGate) {
        List<Floor> floors = parkingLot.getOrderedFloors(entryGate);
        if (floors == null) return null;
        for (Floor floor : floors)
            for (ParkingSpot spot : floor.getParkingSpots()) {
                if (spot.isAvailable() && spot.getSpotType().equals(vehicle.getVehicleType())) {
                    synchronized (spot) {
                        if (spot.isAvailable() && spot.park(vehicle)) return spot;
                    }
                }
            }
        return null;
    }

    public boolean release(ParkingSpot parkingSpot) {
        // FIX: Check within the spot lock so concurrent exits cannot both release it.
        synchronized (parkingSpot) {

            if (!parkingSpot.isAvailable())
                return parkingSpot.unpark();

            throw new IllegalStateException("Parking spot shows unoccupied");
        }
    }
}