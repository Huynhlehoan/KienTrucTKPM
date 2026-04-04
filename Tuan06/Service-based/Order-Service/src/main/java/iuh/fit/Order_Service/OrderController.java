package iuh.fit.Order_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate; // Import Redis thay cho Kafka
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository repo;

    // Đổi từ KafkaTemplate sang StringRedisTemplate
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/")
    public String index(Model model) {
        RestTemplate rest = new RestTemplate();
        // Sync: Gọi Catalog Service lấy Menu [cite: 26, 29]
        List menu = rest.getForObject("http://localhost:8081/api/menu", List.class);
        model.addAttribute("menu", menu);
        return "index";
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam String customerName, @RequestParam String item, @RequestParam Double price) {
        FoodOrder order = new FoodOrder();
        order.setCustomerName(customerName);
        order.setFoodItem(item);
        order.setPrice(price);
        order.setStatus("PAID");
        repo.save(order);

//        // Async: Bắn Event qua Redis [cite: 30, 36]
//        redisTemplate.convertAndSend("order-topic", "OrderID:" + order.getId());

        return "redirect:/?success";
    }
}