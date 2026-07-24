package lld.parking_lot2.model;

import lld.parking_lot2.enums.GateType;

import java.util.Objects;

public abstract class Gate {
    private final String id;
    protected Gate(String id) { this.id = Objects.requireNonNull(id); }
    public final String getId() { return id; }
    public abstract GateType getType();
}
