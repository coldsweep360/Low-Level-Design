# Builder Design Pattern

## Idea

Builder separates the step-by-step construction of a complex object from the finished object. It is useful when a constructor has many optional fields, several parameters share the same type, or an object must be validated before it exists.

Without a builder, an address constructor can become difficult to read and easy to misuse:

```java
new Address("138", "SS ROAD", "Sodepur", "Kolkata");
```

It is not obvious which string belongs to which field. A builder makes each choice named and permits callers to omit optional fields.

## Participants in this implementation

```text
Main -> AddressBuilder (mutable assembly) -> Address (immutable result)
```

- `AddressBuilder` is the mutable builder. It holds partially supplied values while an address is being assembled.
- `Address` is the immutable product: the finished value object.
- `build()` is the boundary between them. It validates required data and creates a new `Address` snapshot.

## Fluent chaining

Every builder setter changes the builder and returns `this`, which is the same builder object:

```java
Address address = new AddressBuilder()
        .setHouseNo("138")
        .setRoadName("SS ROAD")
        .setAddressLine1("Sodepur")
        .setAddressLine2("Kolkata")
        .build();
```

This is equivalent to storing the builder returned from every call and then calling the next method on it. The builder is mutable during this process; fluent chaining does not mean it creates a new builder after each setter.

## Why `Address` is immutable

`Address` is `final`, its fields are `private final`, it has no setters, and its constructor is package-private so clients normally must use `build()`. The fields are `String`s, which are immutable. Therefore a later mutation of the builder cannot modify an address that was already built:

```java
AddressBuilder builder = new AddressBuilder().setRoadName("SS ROAD");
Address first = builder.setHouseNo("138").build();
builder.setHouseNo("999");

System.out.println(first.getHouseNo()); // 138
```

The builder is intentionally mutable and reusable; each `build()` produces a separate immutable result. If `Address` later stores a mutable object (for example, `List<String>`), it must use defensive copies.

## Validation

`roadName` is required. `build()` throws `IllegalStateException` when it is missing or blank, preventing creation of an invalid address.

## When to use it

Use Builder when construction has optional values, named configuration is clearer than positional arguments, or the result should be immutable. It also works well when validation requires several values to be considered together.

For a class with only two or three obvious required parameters, a normal constructor is usually simpler. Builder adds an extra object and more code, so it should improve readability enough to justify that cost.

## Run

```powershell
mvn compile
java -cp target/classes lld.builder.Main
```
