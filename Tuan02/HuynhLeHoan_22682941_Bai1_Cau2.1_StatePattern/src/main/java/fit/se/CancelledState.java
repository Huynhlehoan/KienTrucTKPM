package fit.se;

public class CancelledState implements OrderState{
    @Override
    public void handleRequest() {
        System.out.println("- Trạng thái Hủy: Đã hủy đơn và hoàn tiền.");
    }
}
