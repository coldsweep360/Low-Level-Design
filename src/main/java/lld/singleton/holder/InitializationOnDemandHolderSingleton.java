package lld.singleton.holder;

/**
 * Initialization-on-demand holder singleton.
 *
 * Advantages: lazy, lock-free on the access path, and safe through class
 * initialization. Disadvantages: less familiar syntax and no checked setup
 * outside the holder initializer. Production use cases: preferred general
 * purpose singleton when an enum is not suitable. Complexity: O(1)/O(1).
 * Thread safety: safe; Holder is initialized once when its field is first read.
 * Interview questions: Why is Holder lazy? Which class is initialized first?
 * Common mistakes: putting the instance in the outer class, which makes it eager.
 */
public final class InitializationOnDemandHolderSingleton {
    private InitializationOnDemandHolderSingleton() { }

    private static class Holder {
        // JVM class initialization provides the synchronization boundary.
        private static final InitializationOnDemandHolderSingleton INSTANCE =
                new InitializationOnDemandHolderSingleton();
    }

    public static InitializationOnDemandHolderSingleton getInstance() {
        return Holder.INSTANCE;
    }
}
