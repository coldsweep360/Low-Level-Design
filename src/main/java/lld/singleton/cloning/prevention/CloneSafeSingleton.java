package lld.singleton.cloning.prevention;

/**
 * Singleton that rejects cloning explicitly.
 *
 * Advantages: clone attempts fail loudly and preserve identity. Disadvantages:
 * callers cannot clone the type by design; reflective or unsafe mechanisms are
 * outside this ordinary API guarantee. Production use cases: ordinary class
 * singleton where cloning must be prohibited. Complexity: O(1) access and O(1)
 * space. Thread safety: final class initialization provides safe publication.
 * Interview questions: Why override clone if Cloneable is absent? What does
 * protected visibility prevent? Common mistakes: returning super.clone().
 */
public final class CloneSafeSingleton {
    private static final CloneSafeSingleton INSTANCE = new CloneSafeSingleton();

    private CloneSafeSingleton() { }

    public static CloneSafeSingleton getInstance() {
        return INSTANCE;
    }

    @Override
    protected final Object clone() throws CloneNotSupportedException {
        // Object.clone is intentionally blocked rather than allowing a copy.
        throw new CloneNotSupportedException("Singletons must not be cloned");
    }
}
