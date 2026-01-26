package fit.se;

public class Investor implements Observer{
    private String name;

    public Investor(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("Thông báo gửi tới " + name + ": " + message);
    }
}
