package lld.singleton.eager;

/**
 * Eager singleton created during class initialization.
 *
 * Advantages: simple, immutable reference, and thread-safe publication by the
 * JVM's class-initialization protocol. Disadvantages: allocation occurs even
 * when the object is never used, and construction failures occur at class load.
 * Production use cases: small, mandatory, stateless registries or constants.
 * Complexity: O(1) access and O(1) retained space.
 * Thread safety: safe; class initialization happens once and synchronizes with
 * every subsequent use of the initialized class.
 * Interview questions: Who initializes static fields? Is class initialization
 * lazy? What happens when the constructor throws?
 * Common mistakes: exposing a mutable instance field or doing expensive work
 * that callers may never need.
 */
public final class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
