package lld.parking_lot_2.entity;

import lld.parking_lot_2.service.ParkingSpotAllocation;
import lld.parking_lot_2.service.TicketService;

public class EntryGate {
    private final ParkingSpotAllocation spotAllocator;
    private final TicketService ticketService;

    public EntryGate(ParkingSpotAllocation spotAllocator, TicketService ticketService) {
        this.spotAllocator = spotAllocator;
        this.ticketService = ticketService;
    }

    public Ticket enter(Vehicle vehicle) {
        ParkingSpot parkingSpot = spotAllocator.allocate(vehicle, this);
        // FIX: Do not issue a ticket when allocation failed.
        if (parkingSpot == null) throw new IllegalStateException("No compatible parking spot is available");
        return ticketService.createTicket(vehicle, parkingSpot);
    }
}