package fit.se;

import java.util.HashMap;
import java.util.Map;

public class CachedProductService implements ProductService{
    private ProductService realDb = new RealDatabaseService();
    // Đây là RAM (Cache), lưu tạm dữ liệu để lần sau lấy cho nhanh
    private Map<String, String> cache = new HashMap<>();

    @Override
    public String getProductDetail(String productId) {
        // Bước 1: Kiểm tra xem trong Cache có chưa?
        if (cache.containsKey(productId)) {
            System.out.println("   [Cache - RAM] Đã tìm thấy! Trả về ngay lập tức.");
            return cache.get(productId);
        }

        // Bước 2: Nếu chưa có, buộc phải gọi Database chậm
        System.out.println("   [Cache - RAM] Chưa có dữ liệu. Phải gọi Database...");
        String data = realDb.getProductDetail(productId);

        // Bước 3: Lưu vào Cache để lần sau dùng
        cache.put(productId, data);

        return data;
    }
}
