package lld.parking_lot2;

import lld.parking_lot2.enums.PaymentMode;
import lld.parking_lot2.enums.PricingStrategyType;
import lld.parking_lot2.enums.VehicleType;
import lld.parking_lot2.factory.PricingStrategyFactory;
import lld.parking_lot2.factory.VehicleFactory;
import lld.parking_lot2.model.EntryGate;
import lld.parking_lot2.model.ExitGate;
import lld.parking_lot2.model.ParkingFloor;
import lld.parking_lot2.model.ParkingSpot;
import lld.parking_lot2.model.Ticket;
import lld.parking_lot2.service.ParkingLot;
import lld.parking_lot2.service.PaymentProcessor;
import lld.parking_lot2.utils.DateTimeParser;

import java.time.LocalDateTime;
import java.util.List;

/** End-to-end demo of the instance-scoped, multi-floor design. */
public final class Main {
    private Main() { }

    public static void main(String[] args) {
        ParkingFloor lower = new ParkingFloor("P1", List.of(
                new ParkingSpot("P1-B1", VehicleType.BIKE),
                new ParkingSpot("P1-C1", VehicleType.CAR)));
        ParkingFloor upper = new ParkingFloor("P2", List.of(
                new ParkingSpot("P2-C1", VehicleType.CAR),
                new ParkingSpot("P2-T1", VehicleType.TRUCK)));

        ParkingLot lot = new ParkingLot(List.of(lower, upper),
                PricingStrategyFactory.create(PricingStrategyType.TIME_BASED),
                new PaymentProcessor());
        EntryGate entry = new EntryGate("ENTRY-1", lot);
        ExitGate exit = new ExitGate("EXIT-1", lot);

        LocalDateTime in = DateTimeParser.parse("21 May 7:30 AM 2025");
        LocalDateTime out = DateTimeParser.parse("21 May 10:15 AM 2025");
        Ticket ticket = entry.admit(VehicleFactory.create("KA-01-AB-1234", VehicleType.CAR), in)
                .orElseThrow(() -> new IllegalStateException("parking full"));

        System.out.println("Ticket " + ticket.getId() + " assigned to "
                + ticket.getFloorId() + "/" + ticket.getSpotId());
        System.out.println("Paid: " + exit.checkout(ticket, out, PaymentMode.UPI));

        // Policy replacement affects future checkouts without changing gates or tickets.
        lot.changePricing(PricingStrategyFactory.create(PricingStrategyType.EVENT_BASED));
        System.out.println("Active vehicles: " + lot.activeVehicleCount());
    }
}
