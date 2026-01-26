package fit.se;

public class SpecialEditionDecorator extends BookDecorator{
    public SpecialEditionDecorator(Book book) {
        super(book);
    }

    @Override
    public String getDetails() {
        return wrappedBook.getDetails() + " + [Phiên bản đặc biệt: Bìa cứng mạ vàng]";
    }
}
