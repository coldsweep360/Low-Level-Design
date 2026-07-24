package lld.singleton.doublecheckedlocking;

/**
 * Lazy singleton using double-checked locking.
 *
 * Advantages: lazy allocation and synchronization only during first creation.
 * Disadvantages: more code and correctness depends on volatile. Production
 * use cases: legacy or performance-sensitive code needing lazy construction.
 * Complexity: O(1) amortized access and O(1) space.
 * Thread safety: volatile prevents reordering of allocation/publication and
 * supplies visibility; the synchronized block prevents duplicate creation.
 * Interview questions: Why are both checks needed? What reordering is unsafe?
 * Common mistakes: omitting volatile, locking only the second check, or using
 * a non-final object graph whose later mutation is not safely published.
 */
public final class DoubleCheckedLockingSingleton {
    // volatile creates visibility and ordering guarantees around publication.
    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() { }

    public static DoubleCheckedLockingSingleton getInstance() {
        DoubleCheckedLockingSingleton local = instance;
        if (local == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                local = instance;
                if (local == null) {
                    local = new DoubleCheckedLockingSingleton();
                    instance = local;
                }
            }
        }
        return local;
    }
}
