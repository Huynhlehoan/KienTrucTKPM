package fit.se;

public class Order {
    private OrderState state;

    public Order() {
        this.state = new NewState(); // Mặc định là mới tạo
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void applyState() {
        this.state.handleRequest();
    }
}
