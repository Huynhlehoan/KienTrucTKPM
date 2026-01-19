package fit.se;

public class EmailNotification implements Notification{

    @Override
    public void send(String message) {
        String appName = AppConfig.getInstance().getAppName();
        System.out.println("[Email] Gửi từ " + appName + ": " + message);
    }
}
