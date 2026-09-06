# Behavioral patterns

Behavioral patterns make it easier to decide who performs work, who is told about a change, and how an object's behaviour changes over time.

| Pattern | Simple idea | Run class |
| --- | --- | --- |
| Chain of Responsibility | Pass a request along handlers until one handles it. | `ChainOfResponsibilityExample` |
| Command | Put an action inside an object. | `CommandExample` |
| Interpreter | Represent a small language as objects that evaluate it. | `InterpreterExample` |
| Iterator | Walk through a collection without exposing its storage. | `IteratorExample` |
| Mediator | Let one coordinator manage object conversations. | `MediatorExample` |
| Memento | Save and restore an object's earlier state. | `MementoExample` |
| Observer | Tell subscribers when something changes. | `ObserverExample` |
| State | Change behaviour by changing the current state object. | `StateExample` |
| Strategy | Swap one algorithm for another. | `StrategyExample` |
| Template Method | Keep an algorithm's steps fixed but allow custom steps. | `TemplateMethodExample` |
| Visitor | Add an operation to object types without editing those types. | `VisitorExample` |

## How to think about them

- **Chain of Responsibility** moves a request through a line of possible handlers. Each handler either answers it or passes it on.
- **Command** turns "do this" into an object. That lets a button, queue, log, or undo list carry actions without knowing the receiver.
- **Interpreter** represents a tiny language as an object tree. It is good for small, stable rule languages; a full parser is better for a large language.
- **Iterator** lets callers move through items using `hasNext`/`next` (or a Java `for-each`) while the collection keeps its storage private.
- **Mediator** is a traffic controller. Participants communicate through one coordinator instead of all knowing each other.
- **Memento** is an undo snapshot. The editor creates it, a caretaker stores it, and the editor restores it without exposing internal details.
- **Observer** is a subscription. One publisher announces a change, and each subscriber reacts independently.
- **State** moves a growing set of state-based `if/else` branches into separate state objects. The current state can switch the context to the next state.
- **Strategy** puts interchangeable ways of doing one job behind an interface. The caller can swap the algorithm at runtime.
- **Template Method** keeps the overall recipe in a base class and allows subclasses to fill in selected steps.
- **Visitor** puts a new operation in a visitor. It is useful when product types are stable but operations are added often; the opposite trade-off makes new product types more work.

The general flow is: an object receives an event or request, then delegates work to the right handler, algorithm, state, subscriber, or coordinating object. This keeps one class from becoming a large collection of unrelated decisions.
