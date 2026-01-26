package fit.se;

public class RealDatabaseService implements ProductService {

    @Override
    public String getProductDetail(String productId) {
        System.out.println("   [Database] Đang kết nối ổ cứng để tìm " + productId + " ...");
        try {
            // Giả lập độ trễ mạng/ổ cứng là 4 giây
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "iPhone 15 Pro Max (ID: " + productId + ")";
    }
}
