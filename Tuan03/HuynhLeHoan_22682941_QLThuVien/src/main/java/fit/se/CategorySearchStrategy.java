package fit.se;

import java.util.List;

public class CategorySearchStrategy implements SearchStrategy{
    @Override
    public void search(List<Book> books, String keyword) {
        System.out.println("Tìm theo thể loại '" + keyword + "':");
        for (Book b : books) {
            if (b.getCategory().contains(keyword)) System.out.println(" - " + b.getDetails());
        }
    }
}
