package fit.se;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Stock vinamilk = new Stock("VNM", 100.0f);

        Investor investor1 = new Investor("Nguyễn Văn A");
        Investor investor2 = new Investor("Trần Thị B");

        vinamilk.attach(investor1);
        vinamilk.attach(investor2);

        System.out.println("--- Giá thay đổi lần 1 ---");
        vinamilk.setPrice(105.5f);

        System.out.println("--- Giá thay đổi lần 2 ---");
        vinamilk.setPrice(110.0f);
    }
}