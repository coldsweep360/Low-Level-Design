package lld.ParkingLot.entity;

import lld.ParkingLot.enums.TicketStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class Ticket {

    private String ticketId;
    private Vehicle vehicle;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private ParkingSpot parkingSpot;
    private TicketStatus ticketStatus;

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    // FIX: Completion updates both the exit time and lifecycle status.
    public void complete(LocalDateTime exitTime){
        this.exitTime = exitTime;
        this.ticketStatus = TicketStatus.COMPLETED;
    }
}
