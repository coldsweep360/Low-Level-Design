# Strategy Design Pattern

## Idea

Use Strategy when one task can be performed by several interchangeable algorithms. Put each algorithm behind a common interface and give the context object one of those implementations. The context delegates instead of deciding *how* the task is done.

Without Strategy, every vehicle might duplicate `drive()` or contain `if/else` checks for vehicle type. Changing a driving algorithm would then require editing multiple classes.

## This example

```text
Client -> Vehicle (context) -> DrivingStrategy (strategy interface)
                               |-- NormalDrivingStrategy
                               `-- SportsDrivingStrategy

PassengerVehicle, OffroadVehicle -> NormalDrivingStrategy
SportsVehicle                    -> SportsDrivingStrategy
```

`Vehicle` is the **context**: its `drive()` method calls `strategy.drive()`. `DrivingStrategy` is the **strategy interface**. The normal and sports classes are **concrete strategies**. Each concrete vehicle selects a strategy in its constructor and passes it to the `Vehicle` superclass.

So `PassengerVehicle.drive()` and `SportsVehicle.drive()` are the same operation from the caller's point of view, but the delegated algorithm differs.

## When to use it

Use it when behavior varies independently from the object using it: payment methods, sorting rules, pricing rules, route calculation, compression, or authentication policies. It supports the Open/Closed Principle: add a new strategy class instead of changing the context's behavior branches.

Do not use it merely for one small, stable `if` statement; it adds extra types and wiring.

## Run

```powershell
mvn compile
java -cp target/classes lld.strategy.Client
```
