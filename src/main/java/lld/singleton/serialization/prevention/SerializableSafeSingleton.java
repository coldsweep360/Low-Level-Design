package lld.singleton.serialization.prevention;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Serializable singleton that restores the canonical instance.
 *
 * Advantages: readResolve replaces the deserialized object before it reaches
 * the caller. Disadvantages: serialization adds coupling and readResolve must
 * remain present. Production use cases: legacy serialized configuration or
 * proxy objects. Complexity: O(1) access; stream cost depends on state.
 * Thread safety: class initialization and final INSTANCE are safely published.
 * Interview questions: What does readResolve return? Is it called before the
 * reference is returned? Common mistakes: returning a new object from it or
 * forgetting its exact signature.
 */
public final class SerializableSafeSingleton implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final SerializableSafeSingleton INSTANCE = new SerializableSafeSingleton();

    private SerializableSafeSingleton() { }

    public static SerializableSafeSingleton getInstance() {
        return INSTANCE;
    }

    // ObjectInputStream uses this hook to substitute the canonical object.
    private Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }
}
