package lld.designpatterns.structural;

/** Gives a customer one simple order method while hiding several internal services. */
public class FacadeExample {
    static class Inventory { boolean inStock(String item) { System.out.println("Checked stock"); return true; } }
    static class Payment { void charge() { System.out.println("Charged card"); } }
    static class Shipping { void send(String item) { System.out.println("Shipped " + item); } }
    // The facade is the friendly front door to a more complicated subsystem.
    static class ShopFacade {
        private final Inventory inventory = new Inventory(); private final Payment payment = new Payment(); private final Shipping shipping = new Shipping();
        void placeOrder(String item) { if (inventory.inStock(item)) { payment.charge(); shipping.send(item); } }
    }
    public static void main(String[] args) { new ShopFacade().placeOrder("book"); }
}
