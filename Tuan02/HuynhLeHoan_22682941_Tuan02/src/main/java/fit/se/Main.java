package fit.se;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    // Bước 1: Gọi Singleton lần đầu 
        System.out.println("--- Bắt đầu chương trình ---");
        AppConfig config = AppConfig.getInstance();

        // Bước 2: Sử dụng Factory để tạo đối tượng
        NotificationFactory factory = new NotificationFactory();

        // Case 1: Khách hàng muốn gửi Email
        Notification noti1 = factory.createNotification("EMAIL");
        if (noti1 != null) {
            noti1.send("Chuc mung nam moi!");
        }

        // Case 2: Khách hàng muốn gửi SMS
        Notification noti2 = factory.createNotification("SMS");
        if (noti2 != null) {
            noti2.send("Happy new year!");
        }

        // Kiểm tra Singleton: Gọi lại getInstance lần nữa
        AppConfig config2 = AppConfig.getInstance();
    }
}