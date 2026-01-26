package fit.se;

public class EBook extends Book{
    public EBook(String title, String author, String category) {
        super(title, author, category);
    }
    @Override
    public String getDetails() {
        return "[E-Book] " + super.getDetails();
    }
}
