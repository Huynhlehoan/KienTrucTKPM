package iuh.fit.Catalog_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FoodController {

    @Autowired private MenuItemRepository menuRepo;

    // Giữ nguyên API cho 8082 gọi
    @GetMapping("/api/menu")
    @ResponseBody
    public List<MenuItem> getMenu() {
        return menuRepo.findAll();
    }

    // Thêm hàm này để hiển thị Giao diện Admin
    @GetMapping("/")
    public String adminPage(Model model) {
        model.addAttribute("menu", menuRepo.findAll());
        return "admin";
    }

    // 2. Thêm món mới
    @PostMapping("/admin/add")
    public String addMenu(@RequestParam String name, @RequestParam Double price) {
        MenuItem item = new MenuItem();
        item.setName(name);
        item.setPrice(price);
        menuRepo.save(item);
        return "redirect:/"; // Thêm xong load lại trang
    }

    // 3. Xóa món
    @PostMapping("/admin/delete/{id}")
    public String deleteMenu(@PathVariable Long id) {
        menuRepo.deleteById(id);
        return "redirect:/";
    }

    // 4. Sửa món (Cập nhật thông tin)
    @PostMapping("/admin/update")
    public String updateMenu(@RequestParam Long id, @RequestParam String name, @RequestParam Double price) {
        menuRepo.findById(id).ifPresent(item -> {
            item.setName(name);
            item.setPrice(price);
            menuRepo.save(item); // JPA tự động hiểu là Update nếu ID đã tồn tại
        });
        return "redirect:/";
    }
}