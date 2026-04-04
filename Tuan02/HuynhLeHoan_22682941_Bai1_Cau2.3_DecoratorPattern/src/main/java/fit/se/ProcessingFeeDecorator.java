package fit.se;
// cụ thể
public class ProcessingFeeDecorator extends PaymentDecorator{
    public ProcessingFeeDecorator(Payment payment) {
        super(payment);
    }

    @Override
    public void pay(double amount) {
        double fee = 5.0; // Phí xử lý
        System.out.println("Cộng thêm phí xử lý: " + fee);
        super.pay(amount + fee);
    }
}
