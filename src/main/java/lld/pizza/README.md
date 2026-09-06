# Decorator Design Pattern

## Idea

Decorator adds responsibilities to an individual object by wrapping it, rather than creating a subclass for every possible feature combination. The wrapper has the same interface as the object it wraps, so callers can treat both the base object and a decorated object uniformly.

Without Decorator, a pizza menu with two bases and several optional toppings soon produces a class explosion such as `MargheritaWithMushroomAndExtraCheese`.

## This example

```text
BasePizza (component)
  |-- MargheritaBaseImpl (concrete component)
  `-- PepperoniBaseImpl  (concrete component)

ToppingDecorator (decorator, also a BasePizza)
  |-- MushroomToppingImpl
  `-- ExtraCheeseToppingImpl
```

`BasePizza` is the **component** abstraction. A base pizza supplies the starting `cost()`. `ToppingDecorator` is also a `BasePizza`, but it holds another `BasePizza`. Each concrete topping calls the wrapped pizza's `cost()` and adds its own amount.

For the order in `Order`:

```text
ExtraCheeseToppingImpl
  -> MushroomToppingImpl
       -> MargheritaBaseImpl
```

Calling `cost()` travels inward, then each layer adds its own price while returning. More toppings are composed by adding more wrappers at runtime.

## When to use it

Use Decorator when optional behaviors can be freely combined: toppings, request middleware, logging, compression, encryption, caching, or Java I/O streams.

The trade-off is more small objects and wrappers, which can make debugging object graphs less direct. Use a simple field or a small number of subclasses when combinations are fixed and few.

## Run

```powershell
mvn compile
java -cp target/classes lld.pizza.Order
```
