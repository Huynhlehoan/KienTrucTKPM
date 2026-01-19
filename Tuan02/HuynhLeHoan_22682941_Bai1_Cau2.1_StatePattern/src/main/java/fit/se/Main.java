package fit.se;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Order order = new Order();

        System.out.println("1. Khi vừa đặt hàng:");
        order.applyState();

        System.out.println("2. Khi nhân viên xác nhận:");
        order.setState(new ProcessingState());
        order.applyState();

        System.out.println("3. Khi khách muốn hủy (giả sử trường hợp này):");
        order.setState(new CancelledState());
        order.applyState();
    }
}