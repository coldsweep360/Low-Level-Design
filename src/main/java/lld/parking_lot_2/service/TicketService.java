package lld.parking_lot_2.service;

import lld.parking_lot_2.entity.ParkingSpot;
import lld.parking_lot_2.entity.Ticket;
import lld.parking_lot_2.entity.Vehicle;
import lld.parking_lot_2.enums.TicketStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TicketService {

    // FIX: Store and retrieve tickets by ticketId; entry and exit gates may run concurrently.
    private final Map<String, Ticket> tickets = new ConcurrentHashMap<>();

    public Ticket createTicket(Vehicle vehicle, ParkingSpot parkingSpot) {

        Ticket ticket = Ticket.builder()
                .ticketStatus(TicketStatus.ACTIVE)
                .entryTime(LocalDateTime.now())
                .exitTime(null)
                .vehicle(vehicle)
                .ticketId(UUID.randomUUID().toString())
                .parkingSpot(parkingSpot)
                .build();

        tickets.put(ticket.getTicketId(), ticket);

        return ticket;

    }

    public Ticket getTicket(String ticketId) {
        return tickets.get(ticketId);
    }
}
