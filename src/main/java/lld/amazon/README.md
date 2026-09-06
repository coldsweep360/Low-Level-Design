# Observer Design Pattern

## Idea

Observer creates a one-to-many dependency: when one object changes, it notifies all interested objects automatically. The changing object should know only an observer interface, not whether notifications are emails, SMS messages, logs, or mobile pushes.

This avoids tightly coupling product-stock code to every notification channel.

## This example

```text
Store -> ProductStockObservable (subject) -> StockObserver (observer interface)
                                            |-- EmailNotificationObserver
                                            `-- SmsNotificationObserver
```

`ProductStockObservable` is the **subject**. It owns the subscriber list and provides `add`, `remove`, and `notifyCustomers`. `StockObserver` is the common observer contract. Email and SMS classes are **concrete observers**.

`Store` composes the objects: it registers the observers, then calls `setStock(100)`. The subject detects the meaningful state transition—stock was zero and is now positive—and invokes `update()` on each observer. This is a **push-style** notification: the subject tells observers that an event happened; observers use their own stored data to act.

## When to use it

Use it when an event has multiple independent reactions: stock alerts, UI listeners, domain events, audit logging, or cache invalidation. New observers can be added without changing `ProductStockObservable`.

Be aware of lifecycle concerns in real applications: unregister observers that are no longer needed, decide what happens if one observer fails, and use thread-safe collections or event infrastructure if notifications are concurrent.

## Run

```powershell
mvn compile
java -cp target/classes lld.amazon.Store
```
