package lld.factory;

import java.util.Objects;

/**
 * Simple Factory responsible for creating {@link Troops} products.
 *
 * The factory centralizes the object-creation decision. Without it, every
 * client would need a switch, knowledge of concrete constructors, and a
 * dependency on every product class. With it, clients depend on the stable
 * {@link Troops} abstraction and pass a domain-level {@link TroopType}.
 *
 * The returned reference has compile-time type {@code Troops}. At runtime it
 * is an {@code Archer}, {@code Barbarian}, or {@code Wizard}; Java resolves
 * calls to {@code attack()} and {@code move()} through dynamic dispatch.
 */
public final class TroopsFactory {

    /**
     * Utility class: callers use the stateless factory operation directly and
     * should not create factory objects that carry no state.
     */
    private TroopsFactory() {
        throw new AssertionError("TroopsFactory must not be instantiated");
    }

    /**
     * Creates the concrete troop represented by {@code troopType}.
     *
     * The Java 17 switch expression is exhaustive for the current enum values.
     * If a new enum value is added, the compiler directs the developer to update
     * this centralized creation policy. {@code Objects.requireNonNull} fails
     * immediately with a useful contract error rather than producing a delayed
     * null-related failure in the client.
     *
     * @param troopType domain value describing the requested troop
     * @return a newly created concrete troop exposed through the {@link Troops}
     *         abstraction
     * @throws NullPointerException if {@code troopType} is {@code null}
     */
    public static Troops createTroop(TroopType troopType) {
        Objects.requireNonNull(troopType, "troopType must not be null");

        // The switch creates exactly one product per request. The client does
        // not need to import or know any of these concrete implementation types.
        return switch (troopType) {
            case ARCHER -> new Archer();
            case BARBARIAN -> new Barbarian();
            case WIZARD -> new Wizard();
        };
    }
}
