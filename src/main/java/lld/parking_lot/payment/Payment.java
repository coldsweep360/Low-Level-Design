package lld.parking_lot.payment;

/*
 * Common contract for all payment methods.
 *
 * ExitGate depends on this interface, not on concrete classes like CashPayment
 * or UPIPayment. That makes it easy to add card, wallet, or online payments.
 */
public interface Payment {
    boolean pay(double amount);
}
