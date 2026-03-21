package fit.se.cms_kernelarchiture.core;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.net.URL;
import java.net.URLClassLoader;
import fit.se.cms_kernelarchiture.plugin.CmsPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

@RestController
@RequestMapping("/api/kernel")
@CrossOrigin(origins = "http://localhost:5173")
public class CoreController {

    // Danh sách bài viết (Lưu tạm trên RAM cho nhẹ)
    private List<Map<String, String>> posts = new ArrayList<>();

    // Cơ chế Plugin Registry: Spring tự động gom các class implement CmsPlugin vào đây
    @Autowired
    private List<CmsPlugin> plugins;

    // --- API QUẢN LÝ PLUGIN ---
    @GetMapping("/plugins")
    public List<CmsPlugin> getPlugins() {
        return plugins;
    }

    @PostMapping("/plugins/{id}/toggle")
    public void togglePlugin(@PathVariable String id) {
        for (CmsPlugin p : plugins) {
            if (p.getPluginId().equals(id)) {
                p.setActive(!p.isActive());
                break;
            }
        }
    }

    // --- API QUẢN LÝ NỘI DUNG (CORE CHỨC NĂNG) ---
    @PostMapping("/posts")
    public void createPost(@RequestBody Map<String, String> payload) {
        posts.add(payload);
    }

    @GetMapping("/posts")
    public List<Map<String, String>> getPosts() {
        List<Map<String, String>> processedPosts = new ArrayList<>();

        for (Map<String, String> post : posts) {
            String originalContent = post.get("content");
            String processedContent = originalContent;

            // CORE GỌI PLUGIN NẾU PLUGIN ĐANG BẬT
            for (CmsPlugin plugin : plugins) {
                if (plugin.isActive()) {
                    processedContent = plugin.execute(processedContent);
                }
            }

            // Trả về một bản copy đã được Plugin can thiệp
            processedPosts.add(Map.of(
                    "title", post.get("title"),
                    "content", processedContent
            ));
        }
        return processedPosts;
    }
    // --- API UPLOAD PLUGIN TỪ BÊN NGOÀI ---
    @PostMapping("/plugins/upload")
    public void uploadPlugin(@RequestParam("file") MultipartFile file, @RequestParam("className") String className) {
        try {
            // 1. Tạo thư mục lưu trữ chắc chắn nằm trong thư mục project hiện tại
            Path dirPath = Paths.get("uploaded_plugins");
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            // 2. Lấy đường dẫn file cuối cùng
            Path filePath = dirPath.resolve(file.getOriginalFilename());

            // 3. Dùng Files.copy thay cho transferTo để chống lỗi đường dẫn Temp của Tomcat
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // 4. Dùng phép thuật của Java để đọc file .jar lúc đang chạy
            URLClassLoader classLoader = new URLClassLoader(
                    new URL[]{filePath.toUri().toURL()},
                    this.getClass().getClassLoader()
            );

            // 5. Khởi tạo Plugin và nhét vào danh sách
            Class<?> pluginClass = Class.forName(className, true, classLoader);
            CmsPlugin newPlugin = (CmsPlugin) pluginClass.getDeclaredConstructor().newInstance();

            plugins.add(newPlugin);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi load plugin: " + e.getMessage());
        }
    }

    // --- API XÓA PLUGIN ---
    @DeleteMapping("/plugins/{id}")
    public void deletePlugin(@PathVariable String id) {
        // Chỉ cần xóa khỏi danh sách trong RAM là xong chức năng
        plugins.removeIf(p -> p.getPluginId().equals(id));
    }
}
