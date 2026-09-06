# Design Patterns, in Simple English

This folder is a small Java "pattern library". A **design pattern** is not a copy-and-paste rule. It is a name for a common way to organise code when a familiar problem appears.

There are 23 classic GoF (Gang of Four) patterns. They are grouped by the kind of problem they solve:

| Group | Plain-English question it answers | Patterns |
| --- | --- | --- |
| Creational | How should I create objects? | Abstract Factory, Builder, Factory Method, Prototype, Singleton |
| Structural | How should existing objects fit together? | Adapter, Bridge, Composite, Decorator, Facade, Flyweight, Proxy |
| Behavioral | How should objects communicate and share work? | Chain of Responsibility, Command, Interpreter, Iterator, Mediator, Memento, Observer, State, Strategy, Template Method, Visitor |

## How to read an example

Every Java file has one `main` method and nested, deliberately small classes. Start at `main`, read the comments, and run it. The small size is intentional: first understand the shape of a pattern, then use the same shape with real classes in an application.

```powershell
mvn compile
java -cp target/classes lld.designpatterns.creational.BuilderExample
```

Replace the class name with any example class listed in its category README. On Windows, use `;` instead of `:` only when you later add external libraries to the classpath; these examples need none.

## Choosing a pattern

Use a pattern only when it removes a real source of change or complexity. A single `if` statement is often clearer than five new classes. Patterns are tools, not a checklist to force into every program.

