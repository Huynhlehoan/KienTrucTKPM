package fit.se;

public abstract class BookDecorator extends Book {
    protected Book wrappedBook;

    public BookDecorator(Book book) {
        super(book.getTitle(), book.getAuthor(), book.getCategory());
        this.wrappedBook = book;
    }

    public abstract String getDetails();
}
