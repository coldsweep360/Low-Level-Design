package lld.singleton.staticblock;

/**
 * Eager singleton whose construction is expressed in a static initializer.
 *
 * Advantages: allows checked/conditional setup before assignment while still
 * using JVM class initialization for safe publication. Disadvantages: still
 * eager, and a failed initializer marks the class erroneous for that loader.
 * Production use cases: initialization that needs a small try/catch or several
 * setup statements. Complexity: O(1) access, O(1) retained space.
 * Thread safety: safe because the JVM serializes <clinit> execution.
 * Interview questions: What is <clinit>? Can two threads execute it together?
 * Common mistakes: swallowing initialization exceptions or publishing a field
 * before construction has completed.
 */
public final class StaticBlockSingleton {
    private static final StaticBlockSingleton INSTANCE;

    static {
        // The assignment completes before another thread can observe the class as initialized.
        INSTANCE = new StaticBlockSingleton();
    }

    private StaticBlockSingleton() {
    }

    public static StaticBlockSingleton getInstance() {
        return INSTANCE;
    }
}
