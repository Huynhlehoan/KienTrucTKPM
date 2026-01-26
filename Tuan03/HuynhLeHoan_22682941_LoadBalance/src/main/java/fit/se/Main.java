package fit.se;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LoadBalancer lb = LoadBalancer.getInstance();

        System.out.println("=== KỊCH BẢN 1: Cả 2 Server đều khỏe mạnh ===");
        // Giả sử có 4 user truy cập
        for (int i = 1; i <= 4; i++) {
            System.out.print("User " + i + " gửi request: ");
            Server s = lb.getServer();
            s.handleRequest("Mua hàng");
        }

        System.out.println("\n=== KỊCH BẢN 2: Server A bị Crash ===");
        // Cho Server A (Index 0) chết
        lb.getAllServers().get(0).crash();

        // User tiếp tục truy cập, xem hệ thống có sập không?
        for (int i = 5; i <= 7; i++) {
            System.out.print("User " + i + " gửi request: ");
            Server s = lb.getServer();
            s.handleRequest("Thanh toán");
        }
    }
}