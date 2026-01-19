package fit.se;

public class AppConfig {
    // Biến static giữ instance duy nhất
    private static AppConfig instance;

    // Dữ liệu dùng chung
    private String appName;

    // Constructor Private: Chặn không cho new AppConfig() từ bên ngoài
    private AppConfig() {
        // Giả lập load cấu hình nặng
        System.out.println(">> [System] Đang khởi tạo cấu hình AppConfig...");
        this.appName = "MyNotificationApp v1.0";
    }

    // Method Static: Cung cấp điểm truy cập toàn cầu
    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public String getAppName() {
        return appName;
    }
}
