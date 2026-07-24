package lld.parking_lot;

import lld.parking_lot.Entity.ParkingSpot;
import lld.parking_lot.Entity.Vehicle;
import lld.parking_lot.enums.VehicleType;
import lld.parking_lot.parkinglot.EntranceGate;
import lld.parking_lot.parkinglot.ExitGate;
import lld.parking_lot.parkinglot.ParkingBuilding;
import lld.parking_lot.parkinglot.ParkingLevel;
import lld.parking_lot.parkinglot.ParkingLot;
import lld.parking_lot.payment.CashPayment;
import lld.parking_lot.pricing.CostComputation;
import lld.parking_lot.pricing.FixedPricingStrategy;

import java.util.List;

/** End-to-end demo of the intentionally small, single-building design. */
public final class ParkingLotClient {
    private ParkingLotClient() { }

    public static void main(String[] args) {
        ParkingLevel ground = new ParkingLevel(1, List.of(
                new ParkingSpot("G-M1", VehicleType.MOTORCYCLE),
                new ParkingSpot("G-C1", VehicleType.CAR)));
        ParkingLot lot = new ParkingLot(
                new ParkingBuilding(List.of(ground)),
                new CostComputation(new FixedPricingStrategy(100)));

        EntranceGate entrance = new EntranceGate();
        ExitGate exit = new ExitGate();
        Ticket ticket = entrance.enter(lot, new Vehicle("MH-12-AB-1234", VehicleType.CAR));
        System.out.println("Issued ticket: " + ticket.getId());
        System.out.println("Amount paid: " + exit.exit(lot, ticket, new CashPayment()));
        System.out.println("Active tickets: " + lot.activeTicketCount());
    }
}
