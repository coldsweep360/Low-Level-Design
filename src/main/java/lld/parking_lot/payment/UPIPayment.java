package lld.parking_lot.payment;

/*
 * UPI payment implementation.
 *
 * This class follows the same Payment interface as cash, so ExitGate can use
 * both payment modes through the same method call.
 */
public class UPIPayment implements Payment {

    @Override
    public boolean pay(double amount) {
        System.out.println("UPI paid: " + amount);
        return true;
    }
}
