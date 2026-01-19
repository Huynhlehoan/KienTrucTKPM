package fit.se;

public class ConsumptionTax implements TaxStrategy{
    @Override
    public double calculate(double price) {
        return price *0.05;
    }
}
