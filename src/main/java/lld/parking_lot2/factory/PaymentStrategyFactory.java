package lld.parking_lot2.factory;

import lld.parking_lot2.enums.PaymentMode;
import lld.parking_lot2.strategy.payment.CardPayment;
import lld.parking_lot2.strategy.payment.CashPayment;
import lld.parking_lot2.strategy.payment.PaymentStrategy;
import lld.parking_lot2.strategy.payment.UpiPayment;

import java.util.Objects;

public final class PaymentStrategyFactory {
    private PaymentStrategyFactory() { }
    public static PaymentStrategy create(PaymentMode mode) {
        return switch (Objects.requireNonNull(mode)) {
            case CASH -> new CashPayment();
            case UPI -> new UpiPayment();
            case CARD -> new CardPayment();
        };
    }
}
