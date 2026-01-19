package fit.se;

public class DeliveredState implements OrderState{
    @Override
    public void handleRequest() {
        System.out.println("- Trạng thái Đã giao: Cập nhật thành công. Đơn hàng hoàn tất.");
    }
}
