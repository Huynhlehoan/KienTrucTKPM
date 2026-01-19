package fit.se;

public abstract class PaymentDecorator implements Payment {
    protected Payment wrappedPayment;

    public PaymentDecorator(Payment payment) {
        this.wrappedPayment = payment;
    }

    public void pay(double amount) {
        wrappedPayment.pay(amount);
    }
}
