package lld.parking_lot2.factory;

import lld.parking_lot2.enums.PricingStrategyType;
import lld.parking_lot2.strategy.pricing.EventBasedPricing;
import lld.parking_lot2.strategy.pricing.PricingStrategy;
import lld.parking_lot2.strategy.pricing.TimeBasedPricing;

import java.util.Objects;

public final class PricingStrategyFactory {
    private PricingStrategyFactory() { }
    public static PricingStrategy create(PricingStrategyType type) {
        return switch (Objects.requireNonNull(type)) {
            case TIME_BASED -> new TimeBasedPricing();
            case EVENT_BASED -> new EventBasedPricing();
        };
    }
}
