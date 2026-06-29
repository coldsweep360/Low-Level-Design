package lld.amazon.Observer;

import lld.amazon.Observable.StockObservable;

public class EmailNotificationObserver implements StockObserver {

    private final String email;

    private final StockObservable observable;

    public EmailNotificationObserver(
            String email,
            StockObservable observable) {

        this.email = email;
        this.observable = observable;

    }

    @Override
    public void update() {

        System.out.println("Email sent to " + email + ". Product is back in stock! Current Stock = " + observable.getStock());

    }

}