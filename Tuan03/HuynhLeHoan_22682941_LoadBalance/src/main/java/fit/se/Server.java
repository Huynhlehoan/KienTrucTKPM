package fit.se;

public class Server {
    private String name;
    private boolean isAlive; // Trạng thái: Sống hay Chết

    public Server(String name) {
        this.name = name;
        this.isAlive = true; // Mặc định bật lên là sống
    }

    public String getName() { return name; }

    // Giả lập server bị sập
    public void crash() { this.isAlive = false; }

    // Kiểm tra server còn sống không
    public boolean checkHealth() { return this.isAlive; }

    public void handleRequest(String req) {
        System.out.println("   -> [Server " + name + "] Đang xử lý: " + req);
    }
}
