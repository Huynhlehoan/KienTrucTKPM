package fit.se;

import java.util.List;

public interface SearchStrategy {
    void search(List<Book> books, String keyword);
}
