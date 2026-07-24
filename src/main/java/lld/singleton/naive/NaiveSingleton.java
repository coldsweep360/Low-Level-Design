package lld.singleton.naive;

/**
 * A deliberately naive lazy singleton.
 *
 * Advantages: lazy allocation and very small implementation.
 * Disadvantages: the public constructor permits unlimited instances and the
 * unsynchronized check is racy. Two threads can both observe null and publish
 * different objects. It is therefore unsuitable for production.
 * Production use cases: none; use this only to explain the problem.
 * Complexity: getInstance is O(1) time and O(1) retained space.
 * Thread safety: not thread-safe; there is no happens-before edge between
 * competing callers.
 * Interview questions: What happens when two threads call getInstance?
 * Why does making the field volatile alone not fix the duplicate creation?
 * Common mistakes: making the constructor public, omitting synchronization,
 * and assuming a single JVM-wide instance across multiple class loaders.
 */
public class NaiveSingleton {
    private static NaiveSingleton instance;

    // Public construction intentionally demonstrates how the invariant can be broken.
    public NaiveSingleton() {
    }

    public static NaiveSingleton getInstance() {
        // A context switch between this read and the assignment allows a race.
        if (instance == null) {
            instance = new NaiveSingleton();
        }
        return instance;
    }
}
