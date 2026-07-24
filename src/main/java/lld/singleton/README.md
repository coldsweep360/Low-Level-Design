# Singleton Design Pattern

This package is a Java 17 learning module containing the common Singleton implementations and the principal ways a normal class-based Singleton can lose identity: reflection, serialization, and cloning.

## Package Structure

```text
lld.singleton
|-- naive
|-- eager
|-- staticblock
|-- lazy
|-- synchronizedsingleton
|-- doublecheckedlocking
|-- holder
|-- enumsingleton
|-- reflection
|   |-- attack
|   `-- prevention
|-- serialization
|   |-- attack
|   `-- prevention
`-- cloning
    |-- attack
    `-- prevention
```

Every directory contains an implementation and an independently runnable `Main.java` demonstration.

## Notes and Diagrams

The visual notes are in [`notes/requirements`](notes/requirements):

![Singleton implementations](notes/requirements/SingletonImplementations.png)

![Thread-safety comparison](notes/requirements/ThreadSafety.png)

![Singleton escape hatches](notes/requirements/EscapeHatches.png)

![Package map](notes/requirements/PackageMap.png)

## Implementation Summary

| Implementation | Lazy | Thread-safe | Main lesson |
|---|---:|---:|---|
| `naive` | Yes | No | A private constructor alone is not enough |
| `eager` | No | Yes | Class initialization safely publishes the object |
| `staticblock` | No | Yes | Static initialization can contain setup logic |
| `lazy` | Yes | No | Unsynchronized check-then-act races |
| `synchronizedsingleton` | Yes | Yes | Monitor mutual exclusion and happens-before |
| `doublecheckedlocking` | Yes | Yes | `volatile` prevents unsafe publication/reordering |
| `holder` | Yes | Yes | Nested-class initialization gives lazy lock-free access |
| `enumsingleton` | JVM-managed | Yes | Enum identity also handles serialization and reflection better |

## JVM and Concurrency Takeaways

1. A `new` expression allocates memory, initializes the object, and publishes a reference. Without safe publication, another thread may not observe the expected state.
2. Class initialization is performed once per class loader. The JVM serializes initialization and establishes a happens-before relationship with later uses of the initialized class.
3. A monitor release happens-before a later acquisition of the same monitor. This is why the synchronized implementation is correct.
4. `volatile` provides visibility and ordering for the singleton reference. In double-checked locking, it prevents a reference from becoming visible before construction has completed.
5. Singleton identity is normally scoped to a class loader. Two class loaders can load the same class bytes and create two logically separate Singleton instances.

## Attack Demonstrations

- Reflection can suppress ordinary constructor access checks. A constructor guard can detect the common second invocation; an enum is stronger for ordinary reflection-based attacks.
- Java serialization reconstructs objects without invoking the normal constructor. `readResolve` must return the canonical instance.
- `Object.clone()` performs a field copy and bypasses the constructor. A Singleton should not implement `Cloneable`, and should reject cloning explicitly when inheritance makes it relevant.

## Running the Examples

From the repository root, compile the package with Java 17:

```powershell
$classes = "target/singleton-classes"
New-Item -ItemType Directory -Force $classes | Out-Null
javac -d $classes (rg --files src/main/java/lld/singleton | Where-Object { $_ -like '*.java' })
```

Then run any example, for example:

```powershell
java -cp target/singleton-classes lld.singleton.doublecheckedlocking.Main
java -cp target/singleton-classes lld.singleton.serialization.prevention.Main
```

The `attack` examples intentionally print that identity was broken. They are demonstrations of failure modes, not production implementations.
