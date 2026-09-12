package lld.ParkingLot.entity;

import java.util.*;

public class Floor {
    private int floorNumber;
    private List<ParkingSpot> parkingSpots;

    // FIX: Allows the parking lot setup to associate spots with a floor.
    public Floor(int floorNumber, List<ParkingSpot> parkingSpots) { this.floorNumber = floorNumber; this.parkingSpots = parkingSpots; }

    public int getFloorNumber() { return floorNumber; }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }
}






