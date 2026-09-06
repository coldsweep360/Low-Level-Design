package lld.designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

/** Notifies every subscriber when a product comes back into stock. */
public class ObserverExample {
    interface Subscriber { void update(String message); }
    static class Product {
        private final List<Subscriber> subscribers = new ArrayList<>();
        void subscribe(Subscriber subscriber) { subscribers.add(subscriber); }
        void restock() { subscribers.forEach(s -> s.update("Product is available")); }
    }
    public static void main(String[] args) { Product product = new Product(); product.subscribe(message -> System.out.println("Email: " + message)); product.subscribe(message -> System.out.println("SMS: " + message)); product.restock(); }
}
