package lld.ParkingLot.entity;

import lld.ParkingLot.enums.TicketStatus;
import java.time.LocalDateTime;

public class Ticket {

    private String ticketId;
    private Vehicle vehicle;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private ParkingSpot parkingSpot;
    private TicketStatus ticketStatus;

    private Ticket() {
    }

    public static Builder builder() {
        return new Builder();
    }

    /** Fluent builder for creating tickets without requiring annotation processing. */
    public static final class Builder {
        private final Ticket ticket = new Ticket();

        public Builder ticketId(String ticketId) {
            ticket.ticketId = ticketId;
            return this;
        }

        public Builder vehicle(Vehicle vehicle) {
            ticket.vehicle = vehicle;
            return this;
        }

        public Builder entryTime(LocalDateTime entryTime) {
            ticket.entryTime = entryTime;
            return this;
        }

        public Builder exitTime(LocalDateTime exitTime) {
            ticket.exitTime = exitTime;
            return this;
        }

        public Builder parkingSpot(ParkingSpot parkingSpot) {
            ticket.parkingSpot = parkingSpot;
            return this;
        }

        public Builder ticketStatus(TicketStatus ticketStatus) {
            ticket.ticketStatus = ticketStatus;
            return this;
        }

        public Ticket build() {
            return ticket;
        }
    }

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
