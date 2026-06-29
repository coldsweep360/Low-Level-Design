package lld.amazon.Observable;

import lld.amazon.Observer.StockObserver;

import java.util.ArrayList;
import java.util.List;

public class ProductStockObservable implements StockObservable {

    private final List<StockObserver> observers = new ArrayList<>();

    private int stock;

    @Override
    public void add(StockObserver observer) {
        observers.add(observer);
    }

    @Override
    public void remove(StockObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyCustomers() {

        for (StockObserver observer : observers) {
            observer.update();
        }

    }

    @Override
    public void setStock(int quantity) {

        boolean wasOutOfStock = stock == 0;

        stock += quantity;

        if (wasOutOfStock) {
            notifyCustomers();
        }

    }

    @Override
    public int getStock() {
        return stock;
    }
}