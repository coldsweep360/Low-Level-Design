package lld.singleton.synchronizedsingleton;

/**
 * Lazy singleton using a synchronized accessor.
 *
 * Advantages: correct, lazy, and easy to review. Disadvantages: every call
 * takes the monitor path, even after initialization. Production use cases:
 * simple low-throughput infrastructure where clarity beats micro-optimization.
 * Complexity: O(1) time and O(1) space; lock contention can affect latency.
 * Thread safety: mutual exclusion plus monitor release/acquire gives a
 * happens-before relationship for the constructed object.
 * Interview questions: What exactly does synchronized protect? Is lock
 * elision possible? Common mistakes: synchronizing only the constructor.
 */
public final class SynchronizedSingleton {
    private static SynchronizedSingleton instance;

    private SynchronizedSingleton() { }

    public static synchronized SynchronizedSingleton getInstance() {
        if (instance == null) {
            instance = new SynchronizedSingleton();
        }
        return instance;
    }
}
