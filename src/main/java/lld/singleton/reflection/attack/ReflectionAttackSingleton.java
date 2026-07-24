package lld.singleton.reflection.attack;

/**
 * Ordinary private constructors do not stop reflection when access checks are
 * deliberately disabled. This class is intentionally vulnerable.
 *
 * Advantages: the normal API is lazy and easy to read. Disadvantages: reflective
 * callers can invoke the private constructor and create another object.
 * Production use cases: none as written. Complexity: O(1) time and space.
 * Thread safety: the holder is safe, but the reflection attack bypasses it.
 * Interview questions: Does private mean impossible to invoke? What does
 * setAccessible(true) change? Common mistakes: treating reflection as a normal
 * caller and relying only on access modifiers for security.
 */
public final class ReflectionAttackSingleton {
    private static final ReflectionAttackSingleton INSTANCE = new ReflectionAttackSingleton();

    private ReflectionAttackSingleton() { }

    public static ReflectionAttackSingleton getInstance() {
        return INSTANCE;
    }
}
