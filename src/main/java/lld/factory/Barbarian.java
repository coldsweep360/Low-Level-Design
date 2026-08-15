package lld.factory;

/**
 * Concrete Product representing a barbarian troop.
 *
 * Keeping this implementation behind the {@link Troops} interface means the
 * caller does not need to know the class name, constructor, or construction
 * rules for a barbarian.
 */
final class Barbarian implements Troops {

    /**
     * Package-private constructor keeps direct construction out of external
     * client code; {@link TroopsFactory} owns product creation.
     */
    Barbarian() {
    }

    /**
     * Barbarian-specific close-combat behavior.
     */
    @Override
    public void attack() {
        System.out.println("Barbarian attacks fiercely with a heavy weapon.");
    }

    /**
     * Barbarian-specific movement behavior.
     */
    @Override
    public void move() {
        System.out.println("Barbarian charges directly toward the enemy.");
    }
}
