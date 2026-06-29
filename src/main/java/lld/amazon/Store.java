package lld.amazon;

import lld.amazon.Observable.ProductStockObservable;
import lld.amazon.Observable.StockObservable;
import lld.amazon.Observer.EmailNotificationObserver;
import lld.amazon.Observer.SmsNotificationObserver;

public class Store {

    public static void main(String[] args) {

        StockObservable iphoneStock = new ProductStockObservable();

        EmailNotificationObserver email1 = new EmailNotificationObserver("soham.ghosh@gmail.com", iphoneStock);

        EmailNotificationObserver email2 = new EmailNotificationObserver("shreya.datta@gmail.com", iphoneStock);

        SmsNotificationObserver sms1 = new SmsNotificationObserver("8372627128", iphoneStock);

        iphoneStock.add(email1);
        iphoneStock.add(email2);
        iphoneStock.add(sms1);

        iphoneStock.setStock(100);

    }

}