package lld.ParkingLot;

import lld.ParkingLot.entity.EntryGate;
import lld.ParkingLot.entity.ExitGate;
import lld.ParkingLot.entity.Floor;
import lld.ParkingLot.entity.ParkingSpot;
import lld.ParkingLot.entity.Ticket;
import lld.ParkingLot.entity.Vehicle;
import lld.ParkingLot.enums.TicketStatus;
import lld.ParkingLot.enums.VehicleType;
import lld.ParkingLot.service.FeeCalculator;
import lld.ParkingLot.service.HourlyFeeCalculatorStrategy;
import lld.ParkingLot.service.ParkingLot;
import lld.ParkingLot.service.ParkingSpotAllocation;
import lld.ParkingLot.service.PaymentStrategy;
import lld.ParkingLot.service.TicketService;
import lld.ParkingLot.service.UPIPaymentStrategy;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Runnable application controller for the parking-lot model.
 *
 * <p>It creates a two-floor lot with two entry gates and demonstrates the
 * normal and failure lifecycle scenarios. Run this class directly to see the
 * flows and their assertions.</p>
 */
public final class ParkingLotController {

    private ParkingLotController() {
        // Prevent creating controller objects accidentally: this class is run via main().
    }

    /** Application entry point: start all demonstration scenarios. */
    public static void main(String[] args) {
        ParkingLotController controller = new ParkingLotController();
        controller.runAllScenarios();
    }

    private void runAllScenarios() {
        // Build one shared parking lot. Each scenario leaves it clean for the next one.
        Setup setup = createSetup();

        demonstrateSuccessfulEntryAndExit(setup);
        demonstrateFailedPaymentThenRetry(setup);
        demonstrateNoCompatibleSpot(setup);
        demonstrateInvalidAndDuplicateExit(setup);

        System.out.println("All parking-lot scenarios completed successfully.");
    }

    private Setup createSetup() {
        // A Floor needs its list of spots, and each ParkingSpot needs its Floor.
        // Create the list and floor first, then add spots to that same list.
        List<ParkingSpot> groundSpots = new ArrayList<>();
        Floor groundFloor = new Floor(0, groundSpots);
        groundSpots.add(new ParkingSpot("G-CAR-1", VehicleType.CAR, true, groundFloor));
        groundSpots.add(new ParkingSpot("G-BIKE-1", VehicleType.BIKE, true, groundFloor));

        List<ParkingSpot> firstFloorSpots = new ArrayList<>();
        Floor firstFloor = new Floor(1, firstFloorSpots);
        firstFloorSpots.add(new ParkingSpot("F1-CAR-1", VehicleType.CAR, true, firstFloor));
        firstFloorSpots.add(new ParkingSpot("F1-TRUCK-1", VehicleType.TRUCK, true, firstFloor));

        List<Floor> floors = List.of(groundFloor, firstFloor);
        List<EntryGate> entryGates = new ArrayList<>();
        List<ExitGate> exitGates = new ArrayList<>();
        Map<EntryGate, List<Floor>> orderedFloorsByGate = new HashMap<>();

        // The empty mutable collections are intentionally shared. The ParkingLot
        // needs to exist before its allocator and gates can be constructed; once
        // the gates are built, we add them to the collections owned by the lot.
        ParkingLot parkingLot = new ParkingLot(floors, entryGates, exitGates, orderedFloorsByGate);
        ParkingSpotAllocation allocator = new ParkingSpotAllocation(parkingLot);
        TicketService ticketService = new TicketService();
        FeeCalculator feeCalculator = new HourlyFeeCalculatorStrategy(hourlyRates());

        EntryGate northEntry = new EntryGate(allocator, ticketService);
        EntryGate southEntry = new EntryGate(allocator, ticketService);
        ExitGate mainExit = new ExitGate(feeCalculator, allocator, ticketService);

        entryGates.add(northEntry);
        entryGates.add(southEntry);
        exitGates.add(mainExit);
        orderedFloorsByGate.put(northEntry, List.of(groundFloor, firstFloor));
        orderedFloorsByGate.put(southEntry, List.of(firstFloor, groundFloor));

        return new Setup(northEntry, southEntry, mainExit);
    }

    private Map<VehicleType, Integer> hourlyRates() {
        // EnumMap is a Map specialized for enum keys. It is a good fit for rates
        // because VehicleType is an enum.
        Map<VehicleType, Integer> rates = new EnumMap<>(VehicleType.class);
        rates.put(VehicleType.BIKE, 20);
        rates.put(VehicleType.CAR, 50);
        rates.put(VehicleType.TRUCK, 100);
        return rates;
    }

    private void demonstrateSuccessfulEntryAndExit(Setup setup) {
        // Entry allocates a compatible spot and creates an ACTIVE ticket.
        Ticket carTicket = setup.northEntry.enter(new Vehicle("CAR-101", VehicleType.CAR));
        require("G-CAR-1".equals(carTicket.getParkingSpot().getSpotId()),
                "North gate should prefer the ground-floor car spot");
        setup.mainExit.exit(carTicket, new UPIPaymentStrategy());
        require(carTicket.getTicketStatus() == TicketStatus.COMPLETED, "Paid ticket should be completed");
        require(carTicket.getParkingSpot().isAvailable(), "Completed ticket's spot should be released");

        Ticket truckTicket = setup.southEntry.enter(new Vehicle("TRUCK-201", VehicleType.TRUCK));
        require("F1-TRUCK-1".equals(truckTicket.getParkingSpot().getSpotId()),
                "Truck should use the compatible first-floor spot");
        setup.mainExit.exit(truckTicket, new UPIPaymentStrategy());
    }

    private void demonstrateFailedPaymentThenRetry(Setup setup) {
        Ticket bikeTicket = setup.northEntry.enter(new Vehicle("BIKE-301", VehicleType.BIKE));
        // A lambda is a short implementation of the single-method PaymentStrategy
        // interface. This one deliberately declines every payment amount.
        PaymentStrategy declinedPayment = amount -> false;
        setup.mainExit.exit(bikeTicket, declinedPayment);
        require(bikeTicket.getTicketStatus() == TicketStatus.ACTIVE, "Declined payment must keep ticket active");
        require(!bikeTicket.getParkingSpot().isAvailable(), "Declined payment must keep the spot occupied");

        setup.mainExit.exit(bikeTicket, new UPIPaymentStrategy());
        require(bikeTicket.getTicketStatus() == TicketStatus.COMPLETED, "A later successful payment should complete the ticket");
    }

    private void demonstrateNoCompatibleSpot(Setup setup) {
        Ticket firstCar = setup.northEntry.enter(new Vehicle("CAR-401", VehicleType.CAR));
        Ticket secondCar = setup.southEntry.enter(new Vehicle("CAR-402", VehicleType.CAR));

        expectFailure(() -> setup.northEntry.enter(new Vehicle("CAR-403", VehicleType.CAR)),
                "A full vehicle type must not receive a ticket");

        setup.mainExit.exit(firstCar, new UPIPaymentStrategy());
        setup.mainExit.exit(secondCar, new UPIPaymentStrategy());
    }

    private void demonstrateInvalidAndDuplicateExit(Setup setup) {
        Ticket ticket = setup.northEntry.enter(new Vehicle("CAR-501", VehicleType.CAR));
        setup.mainExit.exit(ticket, new UPIPaymentStrategy());

        expectFailure(() -> setup.mainExit.exit(ticket, new UPIPaymentStrategy()),
                "A completed ticket must not be processed twice");

        Ticket unknownTicket = Ticket.builder().ticketId("missing-ticket").build();
        expectFailure(() -> setup.mainExit.exit(unknownTicket, new UPIPaymentStrategy()),
                "An unknown ticket must be rejected");
    }

    private void expectFailure(Runnable action, String message) {
        // Runnable represents "an action with no input and no return value".
        // The caller supplies that action with a lambda such as () -> gate.enter(...).
        try {
            action.run();
        } catch (IllegalArgumentException | IllegalStateException expected) {
            System.out.println("Expected failure: " + expected.getMessage());
            return;
        }
        throw new IllegalStateException(message);
    }

    private void require(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * Small immutable bundle returned from {@link #createSetup()}.
     *
     * <p>A {@code record} is Java's concise syntax for a data-only class. Java
     * automatically creates private final fields, a constructor, and accessor
     * methods named {@code northEntry()}, {@code southEntry()}, and {@code mainExit()}.
     * It also creates useful {@code equals}, {@code hashCode}, and {@code toString}
     * methods. The components cannot be reassigned after construction.</p>
     */
    private record Setup(EntryGate northEntry, EntryGate southEntry, ExitGate mainExit) {
    }
}
