package lld.parking_lot.payment;

/** Demo card gateway adapter; a real implementation would call a PSP. */
public final class CardPayment implements Payment {
    @Override public boolean pay(double amount) {
        System.out.println("Card payment accepted: " + amount);
        return true;
    }
}
