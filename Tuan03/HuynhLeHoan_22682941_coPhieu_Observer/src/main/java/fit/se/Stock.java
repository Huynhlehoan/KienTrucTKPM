package fit.se;

import java.util.ArrayList;
import java.util.List;

public class Stock implements Subject{
    private List<Observer> observers = new ArrayList<>();
    private String symbol;
    private float price;

    public Stock(String symbol, float price) {
        this.symbol = symbol;
        this.price = price;
    }

    public void setPrice(float newPrice) {
        this.price = newPrice;
        notifyObservers(); // Tự động thông báo khi giá đổi
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update("Cổ phiếu " + symbol + " đã đổi giá thành: " + price);
        }
    }
}
