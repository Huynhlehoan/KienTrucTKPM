package fit.se;

public class SMSNotification implements Notification{
    @Override
    public void send(String message) {
        String appName = AppConfig.getInstance().getAppName();
        System.out.println("[SMS] Gửi từ " + appName + ": " + message);
    }
}
