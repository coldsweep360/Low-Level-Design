package lld.amazon.Observable;

import lld.amazon.Observer.StockObserver;

public interface StockObservable {

    void add(StockObserver obj);
    void remove(StockObserver obj);
    void notifyCustomers();
    void setStock(int stock);
    int getStock();
}
