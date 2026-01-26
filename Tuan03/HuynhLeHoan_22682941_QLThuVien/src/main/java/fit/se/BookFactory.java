package fit.se;

public class BookFactory {
    public static Book createBook(String type, String title, String author, String category) {
        if (type.equalsIgnoreCase("paper")) {
            return new PaperBook(title, author, category);
        } else if (type.equalsIgnoreCase("ebook")) {
            return new EBook(title, author, category);
        }
        return null;
    }
}
