package fit.se;

public class DiscountDecorator extends PaymentDecorator{
    public DiscountDecorator(Payment payment) {
        super(payment);
    }

    @Override
    public void pay(double amount) {
        double discount = 10.0; // Giảm giá
        System.out.println(">> Áp dụng mã giảm giá: -" + discount);
        super.pay(amount - discount);
    }
}
