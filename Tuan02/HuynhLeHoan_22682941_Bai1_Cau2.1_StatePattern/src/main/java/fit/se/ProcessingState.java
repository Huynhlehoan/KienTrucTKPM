package fit.se;

public class ProcessingState implements OrderState {

    @Override
    public void handleRequest() {
        System.out.println("- Trạng thái Đang xử lý: Đang đóng gói và vận chuyển...");
    }
}
