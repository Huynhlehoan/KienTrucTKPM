package fit.se;

public class LibraryUser implements Observer {
    private String name;
    public LibraryUser(String name) { this.name = name; }
    @Override
    public void update(String message) {
        System.out.println("User " + name + " nhận tin: " + message);
    }
}