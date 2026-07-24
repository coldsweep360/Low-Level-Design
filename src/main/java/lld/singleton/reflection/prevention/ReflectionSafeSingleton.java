package lld.singleton.reflection.prevention;

/**
 * A conventional singleton that detects a second reflective constructor call.
 *
 * Advantages: protects against the common reflection demonstration after the
 * singleton has initialized. Disadvantages: the guard is not a complete JVM
 * security boundary; advanced instrumentation, Unsafe, or a separate class
 * loader can defeat the invariant. Production use cases: normal application
 * singleton where reflection is not an adversarial boundary. Complexity: O(1).
 * Thread safety: class initialization safely creates INSTANCE; the constructor
 * guard is only used during controlled initialization. Interview questions:
 * Why does the guard throw? Why is enum stronger? Common mistakes: using a
 * non-final mutable guard or assuming this prevents cross-class-loader copies.
 */
public final class ReflectionSafeSingleton {
    private static boolean constructorAlreadyCalled;
    private static final ReflectionSafeSingleton INSTANCE = new ReflectionSafeSingleton();

    private ReflectionSafeSingleton() {
        // Class initialization invokes this once before exposing INSTANCE.
        if (constructorAlreadyCalled) {
            throw new IllegalStateException("Singleton constructor invoked more than once");
        }
        constructorAlreadyCalled = true;
    }

    public static ReflectionSafeSingleton getInstance() {
        return INSTANCE;
    }
}
