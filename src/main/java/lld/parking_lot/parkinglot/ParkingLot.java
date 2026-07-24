package lld.parking_lot.parkinglot;

import lld.parking_lot.Entity.Vehicle;
import lld.parking_lot.Ticket;
import lld.parking_lot.payment.Payment;
import lld.parking_lot.pricing.CostComputation;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** Small application facade: entry, exit, ticket registry, and payment boundary. */
public final class ParkingLot {
    private final ParkingBuilding building;
    private final CostComputation pricing;
    private final Clock clock;
    private final Map<String, Ticket> activeTickets = new HashMap<>();

    public ParkingLot(ParkingBuilding building, CostComputation pricing) {
        this(building, pricing, Clock.systemUTC());
    }

    public ParkingLot(ParkingBuilding building, CostComputation pricing, Clock clock) {
        this.building = Objects.requireNonNull(building);
        this.pricing = Objects.requireNonNull(pricing);
        this.clock = Objects.requireNonNull(clock);
    }

    public synchronized Ticket enter(Vehicle vehicle) {
        ParkingAssignment assignment = building.allocate(vehicle);
        Ticket ticket = new Ticket(vehicle, assignment.level(), assignment.spot(), LocalDateTime.now(clock));
        activeTickets.put(ticket.getId(), ticket);
        return ticket;
    }

    public synchronized double exit(Ticket ticket, Payment payment) {
        Ticket active = activeTickets.get(ticket.getId());
        if (active != ticket) throw new IllegalArgumentException("Ticket is not active");
        LocalDateTime exitTime = LocalDateTime.now(clock);
        double amount = pricing.compute(ticket, exitTime);
        if (!payment.pay(amount)) throw new IllegalStateException("Payment failed");
        ticket.getSpot().leave(ticket.getVehicle());
        activeTickets.remove(ticket.getId());
        return amount;
    }

    public synchronized int activeTicketCount() { return activeTickets.size(); }
}
