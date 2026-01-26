package fit.se;

import java.util.List;

public class NameSearchStrategy implements SearchStrategy{
    @Override
    public void search(List<Book> books, String keyword) {
        System.out.println("Tìm theo tên '" + keyword + "':");
        for (Book b : books) {
            if (b.getTitle().contains(keyword)) System.out.println(" - " + b.getDetails());
        }
    }
}
