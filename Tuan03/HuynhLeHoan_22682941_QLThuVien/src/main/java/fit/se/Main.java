package fit.se;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // 1. Singleton
        Library lib = Library.getInstance();

        // 4. Observer: Đăng ký người dùng
        LibraryUser user1 = new LibraryUser("Minh");
        lib.registerObserver(user1);

        // 2. Factory: Thêm sách
        Book b1 = BookFactory.createBook("paper", "Design Patterns", "GoF", "IT");
        Book b2 = BookFactory.createBook("ebook", "Clean Code", "Robert C. Martin", "IT");
        Book b3 = BookFactory.createBook("paper", "Dế Mèn Phiêu Lưu Ký", "Tô Hoài", "Văn Học");

        lib.addBook(b1); // User Minh sẽ nhận thông báo
        lib.addBook(b2);
        lib.addBook(b3);

        System.out.println("\n--- 3. Strategy: Tìm kiếm ---");
        lib.findBook(new NameSearchStrategy(), "Code");
        lib.findBook(new CategorySearchStrategy(), "Văn Học");

        System.out.println("\n--- 5. Decorator: Mượn sách đặc biệt ---");
        // Giả sử user muốn mượn cuốn b3 nhưng là bản đặc biệt
        Book specialBook = new SpecialEditionDecorator(b3);
        System.out.println("Đang mượn: " + specialBook.getDetails());
    }
}