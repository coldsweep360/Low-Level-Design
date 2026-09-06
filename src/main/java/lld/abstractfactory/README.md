# Abstract Factory Design Pattern

## Idea

Abstract Factory creates **families of related objects** without making client code depend on their concrete classes. Select one factory, then use it to create every product in the same family.

It is more than a simple factory: a simple factory usually chooses one product; an abstract factory defines several product-creation methods that remain consistent with one another.

## This example

```text
Client -> GUIFactoryProducer -> GUIFactory (abstract factory)
                              |-- WindowsFactory -> WindowsButton, WindowsCheckbox, WindowsTextBox
                              `-- MacFactory     -> MacButton, MacCheckbox, MacTextBox

Abstract products: Button, Checkbox, TextBox
```

`GUIFactory` is the **abstract factory**. It declares `createButton`, `createCheckbox`, and `createTextBox`. `WindowsFactory` and `MacFactory` are **concrete factories**. Each one creates a matching family of concrete products.

`GUIFactoryProducer` selects the family from the OS input. After that, `Client` depends only on `GUIFactory`, `Button`, `Checkbox`, and `TextBox`; it never imports a Windows or Mac concrete product. Choosing `WindowsFactory` guarantees all three created controls are Windows controls.

## When to use it

Use it when several objects must vary together: operating-system widgets, database-provider clients, cloud-provider resources, theme-specific UI controls, or test versus production infrastructure.

It is easy to add a new family, such as Linux: create `LinuxFactory` and Linux versions of every product. It is harder to add a new product type, such as `Menu`, because every factory must gain a `createMenu()` implementation.

## Run

```powershell
mvn compile
java -cp target/classes lld.abstractfactory.client.Client
```
