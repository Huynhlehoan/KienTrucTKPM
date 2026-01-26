package fit.se;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ProductService service = new CachedProductService();

        System.out.println("=== KỊCH BẢN: User xem sản phẩm lần đầu ===");
        long start1 = System.currentTimeMillis();

        // Gọi lần 1
        String product1 = service.getProductDetail("SP001");

        long end1 = System.currentTimeMillis();
        System.out.println("-> Kết quả: " + product1);
        System.out.println("-> Thời gian chạy: " + (end1 - start1) + "ms (Rất chậm)\n");


        System.out.println("=== KỊCH BẢN: User xem lại sản phẩm đó lần 2 ===");
        long start2 = System.currentTimeMillis();

        // Gọi lần 2 (cùng ID SP001)
        String product2 = service.getProductDetail("SP001");

        long end2 = System.currentTimeMillis();
        System.out.println("-> Kết quả: " + product2);
        System.out.println("-> Thời gian chạy: " + (end2 - start2) + "ms");
    }
}