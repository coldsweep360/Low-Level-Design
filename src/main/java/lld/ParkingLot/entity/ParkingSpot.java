package lld.ParkingLot.entity;

import lld.ParkingLot.enums.VehicleType;

public class ParkingSpot {

    private String spotId;
    private VehicleType spotType;
    private boolean isAvailable;
    private Floor floor;

    public ParkingSpot(String spotId, VehicleType spotType, boolean isAvailable, Floor floor) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.isAvailable = isAvailable;
        this.floor = floor;
    }

    public String getSpotId() {
        return spotId;
    }

    public VehicleType getSpotType() {
        return spotType;
    }

    public Floor getFloor() {
        return floor;
    }

    public boolean isAvailable(){
        return this.isAvailable;
    }

    public boolean park(Vehicle vehicle){
        this.isAvailable = false;
        return true;
    }

    public boolean unpark(){
        this.isAvailable = true;
        return true;

    }
}
