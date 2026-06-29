package lld.amazon.Observer;

import lld.amazon.Observable.StockObservable;

public class SmsNotificationObserver implements StockObserver {

    private final String phoneNumber;

    private final StockObservable observable;

    public SmsNotificationObserver(
            String phoneNumber,
            StockObservable observable) {

        this.phoneNumber = phoneNumber;
        this.observable = observable;

    }

    @Override
    public void update() {

        System.out.println("SMS sent to " + phoneNumber + ". Product is back in stock! Current Stock = " + observable.getStock());

    }

}