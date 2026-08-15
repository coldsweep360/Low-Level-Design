package lld.factory;

/**
 * Product abstraction returned by {@link TroopsFactory}.
 *
 * The interface is the contract the client actually needs. The client can
 * invoke the domain operations below without knowing whether the runtime
 * object is an {@link Archer}, {@link Barbarian}, or {@link Wizard}.
 *
 * This is runtime polymorphism: the variable has compile-time type
 * {@code Troops}, while the object stored in it has a concrete runtime type.
 * Java dispatches the overridden method implementation using that runtime
 * type when the method is invoked.
 */
public interface Troops {

    /**
     * Performs this troop's attack behavior.
     */
    void attack();

    /**
     * Performs this troop's movement behavior.
     */
    void move();
}
