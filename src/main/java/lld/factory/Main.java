package lld.factory;

/**
 * Client demonstration for the Simple Factory implementation.
 *
 * Notice that the client knows only the product interface and the supported
 * domain enum. It does not instantiate a concrete product and does not contain
 * a switch mapping types to classes. This keeps creation policy out of the
 * business workflow represented by this demo.
 */
public final class Main {

    /** Prevents accidental construction of this demo holder class. */
    private Main() {
    }

    /**
     * Requests products through the factory and invokes their common contract.
     *
     * @param args command-line arguments; unused by this demonstration
     */
    public static void main(String[] args) {
        // Compile-time type is Troops; runtime object is Archer.
        Troops archer = TroopsFactory.createTroop(TroopType.ARCHER);
        archer.attack();
        archer.move();

        // The client workflow remains identical even though the runtime object
        // and behavior change to Barbarian and Wizard respectively.
        Troops barbarian = TroopsFactory.createTroop(TroopType.BARBARIAN);
        barbarian.attack();
        barbarian.move();

        Troops wizard = TroopsFactory.createTroop(TroopType.WIZARD);
        wizard.attack();
        wizard.move();
    }
}
