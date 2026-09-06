# Structural patterns

Structural patterns explain how objects and classes can be connected without making the system tightly tied together.

| Pattern | Simple idea | Run class |
| --- | --- | --- |
| Adapter | Translate one interface into another. | `AdapterExample` |
| Bridge | Separate two changing dimensions. | `BridgeExample` |
| Composite | Treat one item and a group of items alike. | `CompositeExample` |
| Decorator | Wrap an object to add behaviour. | `DecoratorExample` |
| Facade | Offer one easy door to a complicated subsystem. | `FacadeExample` |
| Flyweight | Share common, read-only data to save memory. | `FlyweightExample` |
| Proxy | Stand in front of another object to control access. | `ProxyExample` |

## How to think about them

- **Adapter** is a plug converter. It translates an old or third-party API into the API your code already uses.
- **Bridge** separates two axes of change. In the example, shapes and drawing methods can change without multiplying classes such as `SvgCircle`, `PixelCircle`, and so on.
- **Composite** treats a single object and a box of objects alike. A folder can contain files and other folders because all implement the same interface.
- **Decorator** is gift wrapping. Each wrapper keeps the original interface and adds one responsibility, so wrappers can be combined.
- **Facade** is a reception desk. It gives callers one friendly method and hides the ordering of many subsystem calls.
- **Flyweight** shares facts that do not change, such as a tree species. Keep per-object facts, such as a tree position, outside the shared object.
- **Proxy** looks like the real service but adds a gate: permission checks, lazy loading, caching, or logging.

The usual flow is: a caller uses a familiar interface, and the structural object either translates, wraps, groups, coordinates, shares, or guards the real objects behind it.
