package lld.parking_lot2.service;

import lld.parking_lot2.enums.PaymentMode;
import lld.parking_lot2.model.ParkingFloor;
import lld.parking_lot2.model.ParkingSpot;
import lld.parking_lot2.model.Ticket;
import lld.parking_lot2.model.Vehicle;
import lld.parking_lot2.strategy.pricing.PricingStrategy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Instance-scoped application service for the complex design.
 *
 * There is intentionally no global Singleton: tests, tenants, or separate
 * facilities can create independent parking-lot instances and inject policies.
 */
public final class ParkingLot {
    private final List<ParkingFloor> floors;
    private final ConcurrentMap<String, Ticket> activeTickets = new ConcurrentHashMap<>();
    private final PaymentProcessor payments;
    private volatile PricingStrategy pricing;

    public ParkingLot(List<ParkingFloor> floors, PricingStrategy pricing, PaymentProcessor payments) {
        this.floors = List.copyOf(Objects.requireNonNull(floors));
        this.pricing = Objects.requireNonNull(pricing);
        this.payments = Objects.requireNonNull(payments);
    }

    /** Concurrent callers may race, but each spot's CAS permits only one winner. */
    public Optional<Ticket> parkVehicle(Vehicle vehicle, LocalDateTime entryTime) {
        Objects.requireNonNull(vehicle);
        Objects.requireNonNull(entryTime);
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.reserve(vehicle);
            if (spot != null) {
                Ticket ticket = new Ticket(vehicle, floor.getId(), spot.getId(), entryTime);
                activeTickets.put(ticket.getId(), ticket);
                return Optional.of(ticket);
            }
        }
        return Optional.empty();
    }

    /** Payment is collected before release; a failed payment therefore retains the reservation. */
    public synchronized double checkout(Ticket ticket, LocalDateTime exitTime, PaymentMode mode) {
        Ticket active = activeTickets.get(ticket.getId());
        if (active != ticket) throw new IllegalArgumentException("ticket is not active");
        double amount = pricing.calculate(ticket, exitTime);
        if (!payments.collect(ticket, amount, mode)) throw new IllegalStateException("payment declined");
        findSpot(ticket).release(ticket.getVehicle().getRegistration());
        activeTickets.remove(ticket.getId(), ticket);
        return amount;
    }

    public void changePricing(PricingStrategy pricing) { this.pricing = Objects.requireNonNull(pricing); }
    public long activeVehicleCount() { return activeTickets.size(); }

    private ParkingSpot findSpot(Ticket ticket) {
        return floors.stream().filter(f -> f.getId().equals(ticket.getFloorId()))
                .flatMap(f -> f.getSpots().stream())
                .filter(s -> s.getId().equals(ticket.getSpotId()))
                .findFirst().orElseThrow(() -> new IllegalStateException("ticket points to missing spot"));
    }
}
