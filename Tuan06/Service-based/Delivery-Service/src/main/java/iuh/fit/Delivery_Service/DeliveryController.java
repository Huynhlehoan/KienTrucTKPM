package iuh.fit.Delivery_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeliveryController {

    @Autowired private OrderRepository orderRepo;

    // Giữ nguyên API cho 8082 gọi sang
    @PostMapping("/api/delivery/process/{orderId}")
    @ResponseBody
    public String processDelivery(@PathVariable Long orderId) {
        orderRepo.findById(orderId).ifPresent(order -> {
            order.setStatus("DELIVERING");
            orderRepo.save(order);
        });
        return "SUCCESS";
    }

    // Thêm hàm này để hiển thị Giao diện Shipper
    @GetMapping("/")
    public String shipperPage(Model model) {
        // Lấy tất cả đơn hàng để Shipper theo dõi
        model.addAttribute("orders", orderRepo.findAll());
        return "shipper";
    }
}