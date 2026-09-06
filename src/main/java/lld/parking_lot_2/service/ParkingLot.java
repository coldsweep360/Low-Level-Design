package lld.parking_lot_2.service;

import lld.parking_lot_2.entity.EntryGate;
import lld.parking_lot_2.entity.ExitGate;
import lld.parking_lot_2.entity.Floor;

import java.util.List;
import java.util.Map;

public class ParkingLot {
    private List<Floor> floors;
    private List<EntryGate> entryGates;
    private List<ExitGate> exitGates;
    private Map<EntryGate, List<Floor>> gateToOrderedFloors;

    // FIX: Makes the lot's ownership and gate-specific floor ordering configurable.
    public ParkingLot(List<Floor> floors, List<EntryGate> entryGates, List<ExitGate> exitGates, Map<EntryGate, List<Floor>> gateToOrderedFloors) {
        this.floors = floors;
        this.entryGates = entryGates;
        this.exitGates = exitGates;
        this.gateToOrderedFloors = gateToOrderedFloors;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public List<EntryGate> getEntryGates() {
        return entryGates;
    }

    public List<ExitGate> getExitGates() {
        return exitGates;
    }

    public Map<EntryGate, List<Floor>> getGateToOrderedFloors() {
        return gateToOrderedFloors;
    }

    public List<Floor> getOrderedFloors(EntryGate entryGate) {
        return gateToOrderedFloors.get(entryGate);
    }
}