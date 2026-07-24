package lld.parking_lot2.model;

import lld.parking_lot2.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/** Ticket is the authoritative identity of one active parking session. */
public final class Ticket {
    private final String id = UUID.randomUUID().toString();
    private final Vehicle vehicle;
    private final String floorId;
    private final String spotId;
    private final LocalDateTime entryTime;
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    public Ticket(Vehicle vehicle, String floorId, String spotId, LocalDateTime entryTime) {
        this.vehicle = Objects.requireNonNull(vehicle);
        this.floorId = Objects.requireNonNull(floorId);
        this.spotId = Objects.requireNonNull(spotId);
        this.entryTime = Objects.requireNonNull(entryTime);
    }

    public synchronized void markPayment(PaymentStatus status) {
        // A failed gateway attempt is retryable while a successful payment is terminal.
        if (paymentStatus == PaymentStatus.SUCCESS) throw new IllegalStateException("ticket already paid");
        paymentStatus = Objects.requireNonNull(status);
    }
    public String getId() { return id; }
    public Vehicle getVehicle() { return vehicle; }
    public String getFloorId() { return floorId; }
    public String getSpotId() { return spotId; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public synchronized PaymentStatus getPaymentStatus() { return paymentStatus; }
}
