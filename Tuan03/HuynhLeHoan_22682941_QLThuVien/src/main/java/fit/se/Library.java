package fit.se;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private static Library instance;
    private List<Book> books;
    private List<Observer> observers;

    private Library() {
        books = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static synchronized Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addBook(Book book) {
        books.add(book);
        notifyObservers("Sách mới về: " + book.getTitle());
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    private void notifyObservers(String msg) {
        for (Observer o : observers) o.update(msg);
    }

    public void findBook(SearchStrategy strategy, String keyword) {
        strategy.search(books, keyword);
    }
}
