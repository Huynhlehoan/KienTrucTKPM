package fit.se;
// lõi = payment decorator
public class CreditCardPayment implements Payment{
    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán " + amount + " bằng Thẻ Tín Dụng.");
    }
}
