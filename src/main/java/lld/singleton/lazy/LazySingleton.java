package lld.singleton.lazy;

/**
 * Lazy singleton without synchronization.
 *
 * Advantages: no allocation until first use and minimal uncontended overhead.
 * Disadvantages: duplicate instances and unsafe publication are possible.
 * Production use cases: educational only. Complexity: O(1) time and space.
 * Thread safety: unsafe; ordinary reads/writes provide neither mutual
 * exclusion nor the required visibility guarantee.
 * Interview questions: Why is this broken even if the constructor is private?
 * Common mistakes: believing a null check is an atomic operation.
 */
public final class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() { }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
