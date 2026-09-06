package lld.designpatterns.structural;

/**
 * Makes an old payment gateway look like the payment interface the new app expects.
 */
public class AdapterExample {
    interface PaymentProcessor {
        void pay(int rupees);
    }

    static class OldBankGateway {
        void makePaymentInPaise(int paise) {
            System.out.println("Bank received " + paise + " paise");
        }
    }

    // The adapter converts both the method name and the unit of money.
    static class BankAdapter implements PaymentProcessor {
        private final OldBankGateway bank = new OldBankGateway();

        public void pay(int rupees) {
            bank.makePaymentInPaise(rupees * 100);
        }
    }

    public static void main(String[] args) {
        PaymentProcessor processor = new BankAdapter();
        processor.pay(250);
    }
}
