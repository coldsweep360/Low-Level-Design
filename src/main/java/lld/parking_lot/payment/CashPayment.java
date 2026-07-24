package lld.parking_lot.payment;

/*
 * Cash payment implementation.
 *
 * In this learning example, payment always succeeds. In a real system this
 * could integrate with a cashier terminal or payment record.
 */
public class CashPayment implements Payment {
    @Override
    public boolean pay(double amount) {
        System.out.println("Cash paid: " + amount);
        return true;
    }
}
