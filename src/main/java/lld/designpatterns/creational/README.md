# Creational patterns

Creational patterns hide or organise object creation. They are useful when the caller should ask for a result without knowing all construction details.

| Pattern | Simple idea | Run class |
| --- | --- | --- |
| Abstract Factory | Make related objects as one matching family. | `AbstractFactoryExample` |
| Builder | Assemble a complicated object step by step. | `BuilderExample` |
| Factory Method | Let a subclass decide which product to create. | `FactoryMethodExample` |
| Prototype | Make a new object by copying a ready-made one. | `PrototypeExample` |
| Singleton | Allow exactly one shared instance. | `SingletonExample` |

For example, run `java -cp target/classes lld.designpatterns.creational.BuilderExample` after `mvn compile`.

## How to think about them

- **Abstract Factory** is like ordering a matching furniture set. Ask one factory for the whole family, so you do not accidentally mix a Windows button with a Mac checkbox.
- **Builder** is like filling an order form. Required details go in first; optional details are added by readable method calls; `build()` produces the finished object.
- **Factory Method** is like asking a delivery company to provide transport. The shared delivery process calls `createTransport()`, while each kind of company picks its own vehicle.
- **Prototype** is like copying last month's document and changing its title. Be careful: if an object contains changeable lists or maps, its copy must also copy those items (a deep copy), not share them by mistake.
- **Singleton** is for one shared thing, such as application configuration. It is easy to overuse because global shared state makes tests and changes harder; pass normal objects where possible.

The general flow is: the client requests an object, the pattern chooses or assembles it, and the client receives a ready-to-use object. The client does not need to know every construction detail.
