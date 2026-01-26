package fit.se;

import java.util.ArrayList;
import java.util.List;
public class LoadBalancer {
    private static LoadBalancer instance;
    private List<Server> servers = new ArrayList<>();
    private int currentIndex = 0;
    private LoadBalancer() {
        // Mua 2 con server về cắm vào
        servers.add(new Server("A (Primary)"));
        servers.add(new Server("B (Backup)"));
    }
    // Hàm lấy instance duy nhất
    public static synchronized LoadBalancer getInstance() {
        if (instance == null) instance = new LoadBalancer();
        return instance;
    }
    // Hàm lấy server ra để dùng (Logic Round Robin + Failover)
    public Server getServer() {
        // Lấy server hiện tại theo lượt
        Server s = servers.get(currentIndex);
        // Logic đổi lượt cho lần sau (0 -> 1 -> 0)
        currentIndex = (currentIndex + 1) % servers.size();
        // CHECK SỨC KHỎE: Nếu server này chết, tự động lấy con kia
        if (!s.checkHealth()) {
            System.out.println("   !!! CẢNH BÁO: Server " + s.getName() + " đã CHẾT. Chuyển hướng sang server khác!");
            // Lấy con server kế tiếp
            return servers.get(currentIndex);
        }
        return s;
    }
    // Hàm hỗ trợ để làm server sập cho vui
    public List<Server> getAllServers() { return servers; }
}
