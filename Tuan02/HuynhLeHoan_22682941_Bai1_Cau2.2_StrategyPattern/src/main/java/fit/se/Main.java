package fit.se;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
// Sản phẩm bình thường áp dụng VAT
        Product milk = new Product(100.0, new VATTax());
        System.out.println("Giá sữa (VAT): " + milk.getFinalPrice());



        // Sản phẩm xa xỉ áp dụng Luxury Tax
        Product diamond = new Product(1000.0, new LuxuryTax());
        System.out.println("Giá kim cương (Luxury): " + diamond.getFinalPrice());
    }
}