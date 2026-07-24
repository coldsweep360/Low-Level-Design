package lld.singleton.cloning.attack;

/**
 * Cloneable singleton vulnerable to Object.clone().
 *
 * Advantages: ordinary access is simple. Disadvantages: clone creates a new
 * object without invoking the private constructor. Production use cases: none
 * as written. Complexity: O(1) for this empty object's clone; real object
 * graphs cost proportional to copied fields. Thread safety: identity is not
 * protected by synchronization. Interview questions: Why is clone special?
 * Common mistakes: implementing Cloneable without overriding clone.
 */
public final class CloneableAttackSingleton implements Cloneable {
    private static final CloneableAttackSingleton INSTANCE = new CloneableAttackSingleton();

    private CloneableAttackSingleton() { }

    public static CloneableAttackSingleton getInstance() {
        return INSTANCE;
    }

    @Override
    public CloneableAttackSingleton clone() {
        try {
            // Object.clone performs a field-by-field copy without the constructor.
            return (CloneableAttackSingleton) super.clone();
        } catch (CloneNotSupportedException exception) {
            throw new AssertionError("Cloneable contract was violated", exception);
        }
    }
}
