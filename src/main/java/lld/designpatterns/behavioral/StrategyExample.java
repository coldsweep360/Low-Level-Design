package lld.designpatterns.behavioral;

/** Lets checkout select a payment algorithm at runtime. */
public class StrategyExample {
    interface PaymentStrategy { void pay(int amount); }
    static class CardPayment implements PaymentStrategy { public void pay(int amount) { System.out.println("Paid Rs " + amount + " by card"); } }
    static class UpiPayment implements PaymentStrategy { public void pay(int amount) { System.out.println("Paid Rs " + amount + " by UPI"); } }
    static class Checkout { private PaymentStrategy strategy; Checkout(PaymentStrategy strategy) { this.strategy = strategy; } void changePaymentMethod(PaymentStrategy strategy) { this.strategy = strategy; } void pay(int amount) { strategy.pay(amount); } }
    public static void main(String[] args) { Checkout checkout = new Checkout(new UpiPayment()); checkout.pay(500); }
}
