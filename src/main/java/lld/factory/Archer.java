package lld.factory;

/**
 * Concrete Product representing an archer troop.
 *
 * The class is package-private deliberately. Consumers should request a troop
 * through {@link TroopsFactory} and program to {@link Troops}; hiding concrete
 * constructors prevents the client from coupling itself to implementation
 * classes and keeps object creation centralized in the factory.
 */
final class Archer implements Troops {

    /**
     * Package-private construction allows only this package's factory to create
     * the product while keeping the public API focused on the abstraction.
     */
    Archer() {
    }

    /**
     * Archer-specific implementation selected through virtual dispatch.
     */
    @Override
    public void attack() {
        System.out.println("Archer attacks from a distance with arrows.");
    }

    /**
     * Archer-specific movement implementation.
     */
    @Override
    public void move() {
        System.out.println("Archer moves quickly to maintain distance.");
    }
}
