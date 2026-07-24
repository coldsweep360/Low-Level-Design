package lld.singleton.enumsingleton;

/**
 * Enum singleton, generally the most robust Java singleton representation.
 *
 * Advantages: JVM-enforced single enum constant per class loader, safe
 * publication, serialization safety, and resistance to ordinary reflection.
 * Disadvantages: cannot extend a class, construction is not lazy in the same
 * way as a holder, and frameworks may prefer a normal type. Production use
 * cases: stateless services, registries, and strategy objects.
 * Complexity: O(1) time and O(1) space. Thread safety: guaranteed by enum and
 * class initialization semantics. Interview questions: How does serialization
 * preserve the constant? Can reflection call an enum constructor? Common
 * mistakes: putting mutable global state in the enum without synchronization.
 */
public enum EnumSingleton {
    INSTANCE;

    public String description() {
        return "enum-singleton";
    }
}
