package lld.factory;

/**
 * Concrete Product representing a wizard troop.
 *
 * Additional wizard dependencies or construction policy can be introduced
 * inside the factory later without changing code that consumes {@link Troops}.
 */
final class Wizard implements Troops {

    /**
     * Package-private constructor intentionally hides direct construction from
     * callers outside the factory package.
     */
    Wizard() {
    }

    /**
     * Wizard-specific magical attack behavior.
     */
    @Override
    public void attack() {
        System.out.println("Wizard attacks by casting a powerful spell.");
    }

    /**
     * Wizard-specific movement behavior.
     */
    @Override
    public void move() {
        System.out.println("Wizard moves by teleporting across the battlefield.");
    }
}
