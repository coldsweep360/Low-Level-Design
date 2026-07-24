package lld.singleton.serialization.attack;

import java.io.Serializable;

/**
 * Serializable singleton without readResolve; deserialization creates a new object.
 *
 * Advantages: demonstrates the regular API clearly. Disadvantages: Java's
 * serialization mechanism reconstructs the object without calling the private
 * constructor, so identity is lost. Production use cases: none as written.
 * Complexity: O(1) singleton access; serialization is proportional to state.
 * Thread safety: access is safe, but serialization breaks identity. Interview
 * questions: Which method restores identity? Why is the constructor skipped?
 * Common mistakes: implementing Serializable without readResolve.
 */
public final class SerializableAttackSingleton implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final SerializableAttackSingleton INSTANCE = new SerializableAttackSingleton();

    private SerializableAttackSingleton() { }

    public static SerializableAttackSingleton getInstance() {
        return INSTANCE;
    }
}
