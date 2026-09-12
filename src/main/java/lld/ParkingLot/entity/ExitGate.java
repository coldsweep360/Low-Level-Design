package lld.ParkingLot.entity;

import lld.ParkingLot.enums.TicketStatus;
import lld.ParkingLot.service.FeeCalculator;
import lld.ParkingLot.service.ParkingSpotAllocation;
import lld.ParkingLot.service.PaymentStrategy;
import lld.ParkingLot.service.TicketService;

import java.time.LocalDateTime;

public class ExitGate {
    private final FeeCalculator calculator;
    private final ParkingSpotAllocation spotAllocator;
    private final TicketService ticketService;

    // FIX: ExitGate depends on the established abstractions and ticket store.
    public ExitGate(FeeCalculator calculator, ParkingSpotAllocation spotAllocator, TicketService ticketService) {
        this.calculator = calculator;
        this.spotAllocator = spotAllocator;
        this.ticketService = ticketService;
    }

    public void exit(Ticket presentedTicket, PaymentStrategy paymentStrategy) {
        Ticket ticket = ticketService.getTicket(presentedTicket.getTicketId());

        if (ticket == null) throw new IllegalArgumentException("Ticket was not found");

        // FIX: One gate processes a ticket's payment, release, and completion sequence at a time.
        synchronized (ticket) {

            if (ticket.getTicketStatus() != TicketStatus.ACTIVE)
                throw new IllegalStateException("Ticket is not active");

            LocalDateTime exitTime = LocalDateTime.now();

            int amount = calculator.calculate(ticket.getEntryTime(), exitTime, ticket.getVehicle().getVehicleType());

            if (paymentStrategy.pay(amount)) {
                if (spotAllocator.release(ticket.getParkingSpot())) {
                    // FIX: Complete only after successful payment and spot release.
                    ticket.complete(exitTime);
                    System.out.println("Thank you! Visit Again!");
                }
            } else {
                System.out.println("Payment failed. Ticket remains active.");
            }
        }
    }
}
