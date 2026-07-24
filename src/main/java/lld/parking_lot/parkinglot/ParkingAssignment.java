package lld.parking_lot.parkinglot;

import lld.parking_lot.Entity.ParkingSpot;

/** Package-level result keeps level/spot pairing together during ticket creation. */
public record ParkingAssignment(ParkingLevel level, ParkingSpot spot) { }
