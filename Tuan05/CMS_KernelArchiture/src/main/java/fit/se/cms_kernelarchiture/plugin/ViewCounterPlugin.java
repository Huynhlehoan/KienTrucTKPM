package fit.se.cms_kernelarchiture.plugin;
import org.springframework.stereotype.Component;

@Component
public class ViewCounterPlugin implements CmsPlugin {
    private boolean active = false; // Mặc định tắt

    @Override public String getPluginId() { return "view-counter"; }
    @Override public String getPluginName() { return "Đếm lượt xem (Tự động cấp số)"; }
    @Override public boolean isActive() { return active; }
    @Override public void setActive(boolean active) { this.active = active; }

    @Override
    public String execute(String content) {
        if (!active) return content;
        // Logic giả lập: Chèn thêm một dòng báo lượt xem ngẫu nhiên vào cuối bài
        int randomViews = (int) (Math.random() * 500) + 1;
        return content + "\n\n👁️ [ViewCounter Plugin]: Bài viết này đã có " + randomViews + " lượt xem.";
    }
}