# Factory Pattern

## Idea

A factory centralizes object-creation decisions. The caller asks for an abstraction; the factory chooses and creates the correct concrete implementation. This removes constructor knowledge and repeated selection logic from clients.

The examples use a **Simple Factory**. It is a useful creation technique, though it is not one of the original GoF patterns. Factory Method and Abstract Factory are related patterns with different extension points.

## Troop example

```text
Main (client) -> TroopsFactory -> Troops (product interface)
                                |-- Archer
                                |-- Barbarian
                                `-- Wizard
```

`TroopType` is the input that expresses the creation decision. `TroopsFactory.createTroop(TroopType)` maps it to a concrete troop. `Main` stores the result as `Troops`, so it calls `attack()` and `move()` through the shared contract instead of depending on `new Archer()`, `new Barbarian()`, or `new Wizard()`.

## Shape example

`factory.ShapeFactory` applies the same idea: it maps a requested shape to a `Circle`, `Square`, or `Rectangle`, all exposed as `Shape`.

## When to use it

Use a factory when creation depends on configuration, input, a type code, environment, or setup that clients should not own. It keeps the construction policy in one place.

The trade-off is that adding a new product requires editing the simple factory's selection logic. When that becomes a problem, consider Factory Method, registration, dependency injection, or Abstract Factory.

## Run

```powershell
mvn compile
java -cp target/classes lld.factory.Main
```
