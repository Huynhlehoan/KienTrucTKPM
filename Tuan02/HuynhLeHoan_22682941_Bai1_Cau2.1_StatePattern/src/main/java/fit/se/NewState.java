package fit.se;

public class NewState implements OrderState {

    @Override
    public void handleRequest() {
        System.out.println("- Trạng thái Mới tạo: Đang kiểm tra thông tin đơn hàng...");
    }
}
