package fit.se;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Giao dịch 1: Thẻ tín dụng có Phí xử lý ---");
        Payment p1 = new ProcessingFeeDecorator(new CreditCardPayment());
        p1.pay(100.0);

        System.out.println("\n--- Giao dịch 2: PayPal vừa có Phí, vừa có Mã giảm giá ---");
        Payment p2 = new DiscountDecorator(
                new ProcessingFeeDecorator(
                        new PayPalPayment()));
        p2.pay(200.0);
    }
}